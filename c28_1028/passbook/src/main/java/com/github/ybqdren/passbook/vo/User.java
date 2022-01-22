package com.github.ybqdren.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /** 用户 id*/
    private Long id;

    /** 基本信息 */
    private BaseInfo baseInfo;

    /** 用户其他信息 */
    private OtherInfo otherInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BaseInfo{
        private String name;
        private Integer age;
        private String sex;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OtherInfo{
        private String phone;
        private String adress;
    }
}
