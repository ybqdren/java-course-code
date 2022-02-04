package com.github.ybqdren.isendserver.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * <h1> pojo 记录一次聊天中的发送方和接收方 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

 @Data
@Table(name = "friends_request")
public class FriendsRequestPojo {
    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "accept_user_id")
    private String acceptUserId;

    /**
     * 发送请求的事件
     */
    @Column(name = "request_date_time")
    private Date requestDateTime;
 }