package com.github.ybqdren.passbook.utils;


import com.github.ybqdren.passbook.vo.Feedback;
import com.github.ybqdren.passbook.vo.GainPassTemplateRequest;
import com.github.ybqdren.passbook.vo.PassTemplate;
import org.apache.commons.codec.digest.DigestUtils;
import org.mortbay.log.Log;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> RowKey 生成器工具类 </h1>
 **/
public class RowKeyGenUtil {
    /**
     * <h2> 根据提供的 PassTemplate 对象生成 RowKey </h2>
     * @param passTemplate  {@link PassTemplate}
     * @return String RowKey
     */
    public static String genPassTemplateRowKey(PassTemplate passTemplate){
        /** 优惠卷 id 和 title 是唯一的 */
        String passInfo = String.valueOf(passTemplate.getId() + "_" + passTemplate.getTitle());
        /**
         * 为什么会使用 md5 ？
         * 因为 HBase 本身是一个集群，有很多的节点，HBase 所有数据都是基于 RowKey 做存储，RowKey 相近的值会存储到一起。
         * 所以如果不将 RowKey 处理的很分散的话，所有的数据都会集中存储在一个节点上，这样不利于负载均衡，拖累查询速度。
         */
        String rowKey = DigestUtils.md5Hex(passInfo);
        Log.info("GenPassTemplateRowKey: {} , {}" , passInfo , rowKey);

        return rowKey;
    }

    /**
     * <h2> 根据提供的领取优惠卷请求生成 RowKey ，只可以在领取优惠卷的时候使用 </h2>
     * Pass RowKey - reversed(userId) + inverse(timestamp) + PassTemplate RowKey       // inverse = Long.MAX_VALUE - System.currentTimeMillis()
     *                                                                                 // 可以根据 RowKey 去判断哪些用户领取了优惠卷
     * @param request {@link GainPassTemplateRequest}
     * @return String RowKey
     */
    public static String genPassRowKey(GainPassTemplateRequest request){
        return new StringBuilder(String.valueOf(request.getUserId())).reverse().toString()
                + (Long.MAX_VALUE - System.currentTimeMillis())
                + genPassTemplateRowKey(request.getPassTemplate());
    }

    /***
     * <h2> 根据 Feedback 构造 RowKey </h2>
     * @param feedback {@link Feedback}
     * @return Strng RowKey
     */
    public static String genFeedbackRowKey(Feedback feedback){
        /** feedback 一定有一个确定的用户 ；对于同一个用户来说，放在一个地方比较好 ；*/
        /**
         * hbase rowkey 通用设计方式：
         *    1. 随机数 id
         *    2. 时间戳
         *
         * 因为用户的 id 和用户数量有关，对于用户量过大后会相同，而后面会加一个随机数，所以我们对他进行反转后，
         * 会让他又不同的前缀，所以也是保证数据的合理分散，将不同用户的数据分散到不同节点上
         *
         * 当前的时间戳就是用户的 feedback 的时间，这样设计可以保证越晚创建的 feedback 在前面，而越晚创建就在后面
         * 这样我们进行查询时，就可以提高速度。
         * */
        return new StringBuilder(String.valueOf(feedback.getUserId())).reverse().toString() +
                (Long.MAX_VALUE - System.currentTimeMillis());     // 时间戳
    }
}
