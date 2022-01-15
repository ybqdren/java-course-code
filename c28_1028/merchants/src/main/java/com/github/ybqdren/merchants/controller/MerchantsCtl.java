package com.github.ybqdren.merchants.controller;

import com.alibaba.fastjson.JSON;
import com.github.ybqdren.merchants.service.IMerchantServ;
import com.github.ybqdren.merchants.vo.CreateMerchantsRequest;
import com.github.ybqdren.merchants.vo.PassTemplate;
import com.github.ybqdren.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> 商户服务 </h1>
 **/

@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {
    /** 商户服务接口 **/
    private final IMerchantServ merchantServ;


    @Autowired
    public MerchantsCtl(IMerchantServ merchantServ) {
        this.merchantServ = merchantServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){  // @RequestBody：将前端传过来的对象进行正确的序列化
        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return merchantServ.createMechants(request);
    }

    @ResponseBody
    @PostMapping("/{id}") // 动态路径  将参数用 @PathVariable 进行标识
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo: {}", id);
        return merchantServ.buildMerchantsInfoById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate(PassTemplate passTemplate){
        log.info("DropPassTemplate: {}",passTemplate);
        return merchantServ.dropPassTemplate(passTemplate);
    }
}
