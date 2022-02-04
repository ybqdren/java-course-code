package com.github.ybqdren.isendserver.pojo.vo;

import lombok.Data;

/**
 * <h1> vo 好友信息 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

@Data
public class MyFriendsVO {
    private String friendUserId;
    private String friendUsername;
    private String friendFaceImage;
    private String friendNickname;
}