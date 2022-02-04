package com.github.ybqdren.isendserver.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * <h1> pojo 用户信息 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

@Data
public class UsersPojo {
    /**
     * 唯一标识，方便后面分库分表时使用
     */
    @Id
    private String id;

    /**
     * 用户名，账号，慕信号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 我的头像，如果没有默认给一张
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 我的头像 （大）
     */
    @Column(name = "face_image_big")
    private String faceImageBig;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 新用户注册后默认后台生成二维码，并且上传到fastdfs
     */
    private String qrcode;

    /**
     * 用户 id
     */
    private String cid;

}