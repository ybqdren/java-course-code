package com.github.ybqdren.passbook.mapper;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.vo.Pass;
import com.github.ybqdren.passbook.vo.PassTemplated;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> HBase Pass Row To PassTemplate Object </h1>
 **/
public class PassRowMapper implements RowMapper<Pass> {

    /** 第一个列族 和属性 */
    private static byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
    private static byte[] USER_ID = Constants.PassTable.USER_ID.getBytes();
    private static byte[] TEMPLATE_ID = Constants.PassTable.TEMPLATE_ID.getBytes();
    private static byte[] TOKEN = Constants.PassTable.TOKEN.getBytes();
    private static byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
    private static byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

    @Override
    public Pass mapRow(Result result, int i) throws Exception {
        Pass pass = new Pass();
        pass.setUserId(Bytes.toLong(result.getValue(FAMILY_I,USER_ID)));
        pass.setTemplateId(Bytes.toString(result.getValue(FAMILY_I,TEMPLATE_ID)));
        pass.setToken(Bytes.toString(result.getValue(FAMILY_I,TOKEN)));

        pass.setAssignedDate(DateUtil.parseDate(Bytes.toString(result.getValue(FAMILY_I,ASSIGNED_DATE))));

        String conDateStr = Bytes.toString(result.getValue(FAMILY_I,CON_DATE));
        if(conDateStr.equals("-1")){
            pass.setConDate(null);  // 优惠卷没有被使用
        }else {
            pass.setConDate(DateUtil.parseDate(Bytes.toString(result.getValue(FAMILY_I, CON_DATE))));
        }

        return pass;
    }
}
