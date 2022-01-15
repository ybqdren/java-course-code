package com.github.ybqdren.merchants.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.ybqdren.merchants.constant.Constants;
import com.github.ybqdren.merchants.constant.ErrorCode;
import com.github.ybqdren.merchants.dao.MerchantsDao;
import com.github.ybqdren.merchants.entity.Merchants;
import com.github.ybqdren.merchants.service.IMerchantServ;
import com.github.ybqdren.merchants.vo.CreateMerchantsRequest;
import com.github.ybqdren.merchants.vo.CreateMerchantsResponse;
import com.github.ybqdren.merchants.vo.PassTemplate;
import com.github.ybqdren.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1></h1>
 **/


@Slf4j
@Service
public class MerchantsServImpl implements IMerchantServ {
    /** Merchants 数据库接口 **/
    @Autowired
    private MerchantsDao merchantsDao;

    /** kafka 的客户端 **/
//    @Autowired
    private KafkaTemplate<String , String> kafkaTemplate;

    public MerchantsServImpl(MerchantsDao merchantsDao, KafkaTemplate<String, String> kafkaTemplate) {
        this.merchantsDao = merchantsDao;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @Override
    public Response createMechants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse createMerchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);

        if(errorCode != ErrorCode.SUCCESS){
            createMerchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            createMerchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }

        response.setData(createMerchantsResponse);

        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        Merchants merchants = merchantsDao.findbyId(id);
        if(null == merchants){
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }

        response.setData(merchants);
        return response;
    }


    @Override
    public Response dropPassTemplate(PassTemplate template){
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);

        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        }else{
            String passTemplate = JSON.toJSONString(template);
            // 使用 kafka 将消息发送出去
            // kafaka 客户端： kafka-console-consumer --bootstrap-server localhost:9092 --topic merchants-template --from-beginning
            kafkaTemplate.send(  // kafka 每条消息都会又 key 和 value
                    Constants.TEMPLATE_TOPIC,     // kafka 的 topic  如果不配置的话，在发送第一条消息时，kafka 会自动帮我们创建
                    Constants.TEMPLATE_TOPIC,     // kafka 的 key
                    passTemplate
            );

            log.info("DropPassTemplates: {}",passTemplate);
        }

        return response;
    }
}
