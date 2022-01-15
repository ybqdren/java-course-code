package com.github.ybqdren.merchants.service.impl;

import com.github.ybqdren.merchants.constant.ErrorCode;
import com.github.ybqdren.merchants.dao.MerchantsDao;
import com.github.ybqdren.merchants.service.IMerchantServ;
import com.github.ybqdren.merchants.vo.CreateMerchantsRequest;
import com.github.ybqdren.merchants.vo.CreateMerchantsResponse;
import com.github.ybqdren.merchants.vo.PassTemplate;
import com.github.ybqdren.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Transactional
    @Override
    public Response createMechants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse createMerchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);

        if(errorCode != ErrorCode.SUCCESS){
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
        return null;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        return null;
    }
}
