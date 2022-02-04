package com.github.ybqdren.isendserver.pojo.vo;

import lombok.Data;

/**
 * <h1> vo 好友请求发送方的信息 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

@Data
public class FriendRequestVO {
    private String sendUserId;
    private String sendUsername;
    private String sendFaceImage;
    private String sendNickname;
}