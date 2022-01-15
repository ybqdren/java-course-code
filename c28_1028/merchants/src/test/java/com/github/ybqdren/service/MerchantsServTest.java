package com.github.ybqdren.service;


import com.alibaba.fastjson.JSON;
import com.github.ybqdren.merchants.service.IMerchantServ;
import com.github.ybqdren.merchants.vo.CreateMerchantsRequest;
import com.github.ybqdren.merchants.vo.PassTemplate;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 商户服务测试类 </h1>
 *
 * <p>
 *     在 junt5 中 @ExtendWith 替换了原来的 @RunWith
 *     更多参考：
 *          https://www.codenong.com/b-junit-5-runwith/
 *          https://www.jianshu.com/p/4648fd55830e <Junit5 和 Junit4 差别>
 * </p>
 *
 * <p> @SpringBootTest 告诉 Spring Boot 查找带 @SpringBootApplication 注解的主配置类，并使用该类启动 Spring </p>
 *
 * <p> 企业级的测试要先验证是否可用 再使用 这里只是写个形式进行演示</p>
 * */

@ExtendWith(value = {SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) // web 环境
public class MerchantsServTest {
    @Autowired
    private IMerchantServ merchantServ;

    @Test
    public void testCreateMerchatServ(){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("ybqdren");
        request.setLogoUrl("ww.github.com/ybqdren");
        request.setBusinessLicenseUrl("ww.github.com/ybqdren");
        request.setAddress("china");

        System.out.println(JSON.toJSONString(merchantServ.createMechants(request)));
    }


    @Test
    public void testBuildMerchantsInfoById(){
        System.out.println(JSON.toJSONString(merchantServ.buildMerchantsInfoById(7)));
    }

    @Test
    public void testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(9);
        passTemplate.setTitle("title: 卡包");
        passTemplate.setSummary("简介： 一个卡包");
        passTemplate.setDesc("详情：一个分布式的卡包");
        passTemplate.setLimit(10000L);
        passTemplate.setHasToken(false);
        passTemplate.setBackgroud(2);
        passTemplate.setStart(new Date());
//        passTemplate.setEnd();
        System.out.println(JSON.toJSONString(
                merchantServ.dropPassTemplate(passTemplate)
        ));
    }
}
