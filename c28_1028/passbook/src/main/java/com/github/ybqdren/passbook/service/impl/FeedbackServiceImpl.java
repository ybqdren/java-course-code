package com.github.ybqdren.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.mapper.FeedbackRowMapper;
import com.github.ybqdren.passbook.service.IFeedbackService;
import com.github.ybqdren.passbook.utils.RowKeyGenUtil;
import com.github.ybqdren.passbook.vo.Feedback;
import com.github.ybqdren.passbook.vo.Response;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 评论功能实现 </h1>
 **/

@Slf4j
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    /** HBase 客户端 */
    private HbaseTemplate hbaseTemplate;

    @Autowired
    public FeedbackServiceImpl(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }


    /** 将 Java 对象存储到 hbase 中 */
    @Override
    public Response createFeedback(Feedback feedback) {
        if(!feedback.validte()){
            log.error("Feedback Error: {}", JSON.toJSONString(feedback));
            return Response.failure("Feedback Error");
        }

        Put put = new Put(Bytes.toBytes(RowKeyGenUtil.genFeedbackRowKey(feedback)));

        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.USER_ID),
                Bytes.toBytes(feedback.getUserId())
        );

        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.TYPE),
                Bytes.toBytes(feedback.getType())
        );

        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.TEMPLATE_ID),
                Bytes.toBytes(feedback.getTemplateId())
        );

        put.addColumn(
                Bytes.toBytes(Constants.Feedback.FAMILY_I),
                Bytes.toBytes(Constants.Feedback.COMMENT),
                Bytes.toBytes(feedback.getComment())
        );

        hbaseTemplate.saveOrUpdate(Constants.Feedback.TABLE_NAME , put);
        return Response.success();

    }

    /** 将 hbase 中的字节数组转换为 Java 对象 */
    @Override
    public Response getFeedback(Long userId) {
        // 在设置 userid 到 hbase 中的时候，我们就是使用了反转，将随机数反转到了开头，所以这里要将随机数翻转到后面，然后将 userid 翻转到前面
        byte[] reverseUserId = new StringBuilder(String.valueOf(userId)).reverse().toString().getBytes();

        // 使用扫描器对 HBase 中的表进行扫描
        Scan scan = new Scan();
        // 使用过滤器 PrefixFilter（前缀过滤器） 这个会将当前用户的所有 feedback 都扫描出来
        scan.setFilter(new PrefixFilter(reverseUserId));

        List<Feedback> feedbacks = hbaseTemplate.find(Constants.Feedback.TABLE_NAME,scan,new FeedbackRowMapper());

        return new Response(feedbacks);
    }
}
