package com.github.ybqdren.passbook.service;

import com.alibaba.fastjson.JSON;
import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.vo.PassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 消费 Kafka 中的 PassTemplate</h1>
 *
 * 此 bean 被自动注入后，会发现一个 Kafka 的注解  {@link  KafkaListener} ，就知道这是一个消费者，
 * 然后就按照参数中使用的注解取依次取注入这些参数。
 **/

@Slf4j
@Component
public class ConsumePassTemplate {
    /** pass 相关的 HBase 服务 */
    private final IHBasePassService passService;

    @Autowired
    public ConsumePassTemplate(IHBasePassService passService) {
        this.passService = passService;
    }

    /** 参数： String passTemplate , String key , int partition , String topic */
    /**
     * Payload 从 Kafka 中接收的数据是什么
     * Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) 用于写入分区
     * Header(KafkaHeaders.RECEIVED_PARTITION_ID) pratition 的 id
     * Header(KafkaHeaders.RECEIVED_TOPIC) kafka 的 topic
     *
     * @param passTemplate
     */
    @KafkaListener(topics = {Constants.TEMPLATE_TOPIC})
    public void receive(@Payload String passTemplate,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition ,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info("Consumer Receive PassTemplate: {}", passTemplate);
        PassTemplate pt;

        try{
            pt = JSON.parseObject(passTemplate, PassTemplate.class);
        }catch (Exception e){
            log.error("Parse PassTemplate Error: {}" , e.getMessage());
            return;
        }

        log.info("DropPassTemplateToHBase: {}" , passService.dropPassTemplateToHBase(pt));
    }
}
