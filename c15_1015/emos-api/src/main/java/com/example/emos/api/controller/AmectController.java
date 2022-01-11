package com.example.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.emos.api.common.util.PageUtils;
import com.example.emos.api.common.util.R;
import com.example.emos.api.controller.form.*;
import com.example.emos.api.db.pojo.TbAmect;
import com.example.emos.api.service.AmectService;
import com.example.emos.api.websocket.WebSocketService;
import com.example.emos.api.wxpay.WXPayUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/amect")
@Tag(name="AmectController",description = "罚款Web接口")
@Slf4j
public class AmectController {
    @Autowired
    private AmectService amectService;

    @Value("${wx.key}")
    private String key;


    /**
     * 查询罚款信息
     *      1. 数据来源
     *          - 系统自动生成
     *          - 手动添加的罚款
     *
     *      2. 有条件分页查询
     *          - 姓名
     *          - 部门
     *          - 罚款类型
     *          - 日期范围
     *          - 缴纳状态
     *      3.查询每条罚款记录是否与访问当前页面的用户有关
     * @param form
     * @return
     */
    @PostMapping("/searchAmectByPage")
    @Operation(summary = "查询罚款分页记录")
    @SaCheckLogin
    public R searchAmectByPage(@Valid @RequestBody SearchAmectByPageForm form){
        if((form.getStartDate()!=null&&form.getEndDate()==null)||(form.getStartDate()==null&&form.getEndDate()!=null)){
            return R.error("startDate和endDate只能同时为空，或者不为空");
        }
        int page= form.getPage();
        int length= form.getLength();
        int start=(page-1)*length;
        HashMap param= JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start",start);
        param.put("currentUserId", StpUtil.getLoginIdAsInt());
        if(!(StpUtil.hasPermission("AMECT:SELECT")||StpUtil.hasPermission("ROOT"))){
            param.put("userId", StpUtil.getLoginIdAsInt());
        }
        PageUtils pageUtils=amectService.searchAmectByPage(param);
        return R.ok().put("page",pageUtils);

    }

    /**
     * 添加罚款记录
     *      1. 可以选择多个当事人，自动添加多条罚款记录
     *      2. 罚款金额单位是元
     * @param form
     * @return
     */
    @PostMapping("/insert")
    @Operation(summary = "添加罚款记录")
    @SaCheckPermission(value = {"ROOT", "AMECT:INSERT"}, mode = SaMode.OR)
    public R insert(@Valid @RequestBody InsertAmectForm form){
        ArrayList<TbAmect> list=new ArrayList<>();
        for(Integer userId:form.getUserId()){
            TbAmect amect=new TbAmect();
            amect.setAmount(new BigDecimal(form.getAmount()));
            amect.setTypeId(form.getTypeId());
            amect.setReason(form.getReason());
            amect.setUserId(userId);
            amect.setUuid(IdUtil.simpleUUID());
            list.add(amect);
        }
        int rows= amectService.insert(list);
        return R.ok().put("rows",rows);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查找罚款记录")
    @SaCheckPermission(value = {"ROOT", "AMECT:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchAmectByIdForm form){
        HashMap map=amectService.searchById(form.getId());
        return R.ok(map);
    }

    /**
     * 更新罚款记录：
     *       1. 只能修改罚款内容
     *       2. 不能修改罚款当事人
     * @param form
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "更新罚款记录")
    @SaCheckPermission(value = {"ROOT", "AMECT:UPDATE"}, mode = SaMode.OR)
    public R update(@Valid @RequestBody UpdateAmectForm form){
        HashMap param=JSONUtil.parse(form).toBean(HashMap.class);
        int rows=amectService.update(param);
        return R.ok().put("rows",rows);
    }

    /**
     * 删除罚款记录
     *  1. 可以同时删除多罚款记录
     *  2. 因为无法修改罚款当事人，所以可以先删除罚款，然后创建新的罚款
     * @param form
     * @return
     */
    @PostMapping("/deleteAmectByIds")
    @Operation(summary = "删除罚款记录")
    @SaCheckPermission(value = {"ROOT", "AMECT:DELETE"}, mode = SaMode.OR)
    public R deleteAmectByIds(@Valid @RequestBody DeleteAmectByIdsForm form){
        int rows=amectService.deleteAmectByIds(form.getIds());
        return R.ok().put("rows",rows);
    }


    /**
     * 缴纳罚款：
     *      （本课程使用成熟稳定的 V2 版本微信支付 API 接口）
     *
     *      1. 商品订单
     *          - 罚款记录等价于商品订单
     *          - uuid 字段充当商品 ID
     *
     *      2. 支付订单
     *          - 有微信平台生成支付订单
     *          - 调用的时候需传入若干参数
     *          - 支付信息需要后端生成付款二维码
     *
     *      3. 接收付款通知
     *          - 微信平台会把付款结果发送给移动端和商户系统
     *          - 配置内网穿透
     *              --> 因为本地电脑没有外网静态 IP，所以收不到付款结果通知
     *              --> 量子互联
     *                  --- 每月10元
     *                  --- 不限流量
     *
     *          - 更新罚款单为已付款
     *
     *      4. 推送付款结果给前端页面
     *          - 利用 WebSocket 技术
     *          - 后端系统缓存 Session 连接
     *          - 利用缓存的 session 给前端推送消息
     *          - 为了避免 WebSocket 连接超时，需要客户端轮询发 ping 请求
     *
     *      5. 主动查询付款结果
     *          - 商户系统和手机端都可能没有收到付款结果通知
     *          - 用户点击页面按钮，主动查询付款结果
     *
     * @param form
     * @return
     */
    @PostMapping("/createNativeAmectPayOrder")
    @Operation(summary = "创建Native支付罚款订单")
    @SaCheckLogin
    public R createNativeAmectPayOrder(@Valid @RequestBody CreateNativeAmectPayOrderForm form){
        int userId=StpUtil.getLoginIdAsInt();
        int amectId= form.getAmectId();
        HashMap param=new HashMap(){{
            put("userId",userId);
            put("amectId",amectId);
        }};
        String qrCodeBase64= amectService.createNativeAmectPayOrder(param);
        return R.ok().put("qrCodeBase64",qrCodeBase64);
    }

    @RequestMapping("/recieveMessage")
    @Operation(summary = "接收通知消息")
    public void recieveMessage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("utf-8");
        Reader reader=request.getReader();
        BufferedReader buffer=new BufferedReader(reader);
        String line= buffer.readLine();
        StringBuffer temp=new StringBuffer();
        while (line!=null){
            temp.append(line);
            line=buffer.readLine();
        }
        buffer.close();
        reader.close();
        String xml=temp.toString();
        if(WXPayUtil.isSignatureValid(xml,key)){
            Map<String,String> map=WXPayUtil.xmlToMap(xml);
            String resultCode = map.get("result_code");
            String returnCode = map.get("return_code");
            if("SUCCESS".equals(resultCode)&&"SUCCESS".equals(returnCode)){
                String outTradeNo = map.get("out_trade_no");
                HashMap param=new HashMap(){{
                   put("status",2);
                   put("uuid",outTradeNo);
                }};
                int rows=amectService.updateStatus(param);
                if(rows==1){
                    //向前端页面推送付款结果
                    int userId=amectService.searchUserIdByUUID(outTradeNo);
                    WebSocketService.sendInfo("收款成功",userId+"");
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/xml");
                    Writer writer=response.getWriter();
                    BufferedWriter bufferedWriter=new BufferedWriter(writer);
                    bufferedWriter.write("<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>");
                    bufferedWriter.close();
                    writer.close();

                }
                else{
                    log.error("更新订单状态失败");
                    response.sendError(500, "更新订单状态失败");
                }
            }

        }
        else {
            log.error("数字签名异常");
            response.sendError(500, "数字签名异常");
        }
    }

    @PostMapping("/searchNativeAmectPayResult")
    @Operation(summary = "查询Native支付罚款订单的结果")
    @SaCheckLogin
    public R searchNativeAmectPayResult(@Valid @RequestBody SearchNativeAmectPayResultForm form){
        int userId=StpUtil.getLoginIdAsInt();
        int amectId=form.getAmectId();
        HashMap param=new HashMap(){{
            put("amectId",amectId);
            put("userId",userId);
            put("status",1);
        }};
        amectService.searchNativeAmectPayResult(param);
        return R.ok();
    }

    @PostMapping("/searchChart")
    @Operation(summary = "查询Chart图表")
    @SaCheckPermission(value = {"ROOT", "AMECT:SELECT"}, mode = SaMode.OR)
    public R searchChart(@Valid @RequestBody SearchChartForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        HashMap map = amectService.searchChart(param);
        return R.ok(map);
    }
}
