package com.github.ybqdren.passbook.mapper;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.vo.PassTemplate;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> HBase PassTemplate Row To PassTemplate Object </h1>
 **/
public class PassTemplateRowMapper implements RowMapper<PassTemplate> {

    /** 第一个列族 和属性 */
    private static byte[] FAMILY_B = Constants.PassTemplateTable.FAMILY_B.getBytes();
    private static byte[] ID = Constants.PassTemplateTable.ID.getBytes();
    private static byte[] TITLE = Constants.PassTemplateTable.TITLE.getBytes();
    private static byte[] SUMMARY = Constants.PassTemplateTable.SUMMARY.getBytes();
    private static byte[] DESC = Constants.PassTemplateTable.DESC.getBytes();
    private static byte[] HAS_TOKEN = Constants.PassTemplateTable.HAS_TOKEN.getBytes();
    private static byte[] BACKGROUND = Constants.PassTemplateTable.BACKGROUND.getBytes();

    /** 第二个列族 和属性 */
    private static byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
    private static byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();
    private static byte[] START = Constants.PassTemplateTable.START.getBytes();
    private static byte[] END = Constants.PassTemplateTable.END.getBytes();

    @Override
    public PassTemplate mapRow(Result result, int i) throws Exception {
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(Bytes.toInt(result.getValue(FAMILY_B,ID)));
        passTemplate.setTitle(Bytes.toString(result.getValue(FAMILY_B,TITLE)));
        passTemplate.setSummary(Bytes.toString(result.getValue(FAMILY_B,SUMMARY)));
        passTemplate.setDesc(Bytes.toString(result.getValue(FAMILY_B,DESC)));
        passTemplate.setHasToken(Bytes.toBoolean(result.getValue(FAMILY_B,HAS_TOKEN)));
        passTemplate.setBackground(Bytes.toInt(result.getValue(FAMILY_B,BACKGROUND)));

//        String[] patterns = new String[]{"yyyy-MM-dd"};
//        String[] patterns = new String[]{"yyyy-MM-dd"};

        // hbase 中存储的是字节数组，所以如果时间原类型为时间，最好是转换一下
        passTemplate.setLimit(Bytes.toLong(result.getValue(FAMILY_C,LIMIT)));
        passTemplate.setStart(DateUtil.parseDate(Bytes.toString(result.getValue(FAMILY_C,START))));
        passTemplate.setEnd(DateUtil.parseDate(Bytes.toString(result.getValue(FAMILY_C,END))));

        return passTemplate;
    }
}
