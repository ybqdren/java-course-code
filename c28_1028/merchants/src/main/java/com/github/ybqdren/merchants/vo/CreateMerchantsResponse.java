package com.github.ybqdren.merchants.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/14 12:51
 * @package com.github.ybqdren.merchants.vo
 * @description
 *
 * <h1> 创建商户相应对象 </h1>
 * <p> 和 {@link CreateMerchantsRequest} 是对应的关系，一个是请求，一个是响应。 </p>
 * <p> 此处没有对外定义 errorcode 之类，是因为我们不想对外暴露我们的错误。 这个按照自己的需求来进行定义。 </p>
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {
    /** 商户 id: 创建失败则为 -1 **/
    private Integer id;



}
