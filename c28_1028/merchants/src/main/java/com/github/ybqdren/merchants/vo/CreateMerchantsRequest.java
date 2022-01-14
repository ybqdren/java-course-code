package com.github.ybqdren.merchants.vo;

import com.github.ybqdren.merchants.constant.ErrorCode;
import com.github.ybqdren.merchants.dao.MerchantsDao;
import com.github.ybqdren.merchants.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/14 12:39
 * @package com.github.ybqdren.merchants.vo
 * @description
 * <h1> 创建商户请求对象 </h1>
 * <p> 映射到 Merchants 那张表 </p>
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {
    /** 商户名称 **/
    private String name;

    /** 商户 logo **/
    private String logoUrl;

    /** 商户营业执照 **/
    private String businessLicenseUrl;

    /** 商户联系电话 **/
    private String phone;

    /** 商户地址 **/
    private String address;

    /**
     * <h2> 验证请求的有效性 </h2>
     * @param merchantsDao {@link MerchantsDao}
     * @return {@link  ErrorCode}
     */
    private ErrorCode validate(MerchantsDao merchantsDao){
        if( null == this.name ){
            return ErrorCode.EMPTY_NAME;
        }

        if(merchantsDao.findByName(this.name) != null){
            return ErrorCode.DUPLICATE_NAME;
        }

        if(null == this.logoUrl){
            return ErrorCode.EMPTY_LOGO;
        }

        if(null == this.businessLicenseUrl){
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }

        if(null == this.address){
            return ErrorCode.EMPTY_ADDRESS;
        }

        if(null == this.phone){
            return ErrorCode.ERROR_PHONE;
        }

        return ErrorCode.SUCCESS;
    }

    /**
     * <h2> 将请求对象转换为商户对象 </h2>
     * @return {@link Merchants}
     */
    public Merchants toMerchants(){
        Merchants merchants = new Merchants();
        merchants.setName(this.name);
        merchants.setLogoUrl(this.logoUrl);
        merchants.setBusinessLicenseUrl(this.businessLicenseUrl);
        merchants.setPhone(this.phone);
        merchants.setAddress(this.address);

        return merchants;
    }
}
