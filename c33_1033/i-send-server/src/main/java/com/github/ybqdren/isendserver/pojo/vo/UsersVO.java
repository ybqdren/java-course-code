package com.github.ybqdren.isendserver.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <h1> vo 用户信息 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

@Data
public class UsersVO {
    private String id;
    private String username;
    private String faceImage;
    private String faceImageBig;
    private String nickname;
    private String qrcode;
}