package com.github.ybqdren.passbook.mapper;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.vo.Feedback;
import com.github.ybqdren.passbook.vo.Pass;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> HBase Feedback Row To PassTemplate Object </h1>
 **/
public class FeedbackRowMapper implements RowMapper<Feedback> {

    /** 第一个列族 和属性 */
    private static byte[] FAMILY_I = Constants.Feedback.FAMILY_I.getBytes();
    private static byte[] USER_ID = Constants.Feedback.USER_ID.getBytes();
    private static byte[] TYPE = Constants.Feedback.TYPE.getBytes();
    private static byte[] TEMPLATE_ID = Constants.Feedback.TEMPLATE_ID.getBytes();
    private static byte[] COMMENT = Constants.Feedback.COMMENT.getBytes();

    @Override
    public Feedback mapRow(Result result, int i) throws Exception {
        Feedback feedback = new Feedback();
        feedback.setUserId(Bytes.toLong(result.getValue(FAMILY_I,USER_ID)));
        feedback.setType(Bytes.toString(result.getValue(FAMILY_I,TYPE)));
        feedback.setTemplateId(Bytes.toString(result.getValue(FAMILY_I,TEMPLATE_ID)));
        feedback.setComment(Bytes.toString(result.getValue(FAMILY_I,COMMENT)));

        return feedback;
    }
}
