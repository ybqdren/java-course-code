package com.github.ybqdren.merchants.vo;

import com.github.ybqdren.merchants.constant.ErrorCode;
import com.github.ybqdren.merchants.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import java.util.Date;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/14 12:26
 * @package com.github.ybqdren.merchants.vo
 * @description vo (value object) 在 service 之间作为业务传递的存在
 *
 * <h1> 投放的优惠券对象定义 </h1>
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {
    /** 所属商户 id **/
    private Integer id;

    /** 优惠券标题 **/
    private String title;

    /** 优惠券摘要 **/
    private String summary;

    /** 优惠券的详细信息 **/
    private String desc;

    /** 最大个数限制 **/
    private Long limit;

    /** 优惠券是否有 Token ，用于商户核销 **/
    private Boolean hasToken; // token 存储与 redis set 中，每次领取从 Redis 中获取

    /** 优惠券背景色 **/
    private Integer backgroud;

    /** 优惠券开始时间 **/
    private Date start;

    /** 优惠卷结束时间 **/
    private Date end;

    /**
     * <h2> 校验优惠券对象的有效性 </h2>
     * <p> 商户在投放优惠券时一定要携带其商户 id ，商户信息在投放之前一定要在数据库中保存，此时就是检查是否存在此商户 </p>
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link ErrorCode}
     */
    public ErrorCode validate(MerchantsDao merchantsDao){
        if(null == merchantsDao.findbyId(this.id)){
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }

        return ErrorCode.SUCCESS;
    }
}
