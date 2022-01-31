package com.github.ybqdren.isendserver.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;


@Data
public class UsersVO {
    private String id;
    private String username;
    private String faceImage;
    private String faceImageBig;
    private String nickname;
    private String qrcode;
}