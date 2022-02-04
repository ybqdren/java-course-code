package com.github.ybqdren.isendserver.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <h1> pojo 聊天消息 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

@Data
@Table(name = "chat_msg")
public class ChatMsgPojo {
    @Id
    private String id;

    @Column(name = "send_user_id")
    private String sendUserId;

    @Column(name = "accept_user_id")
    private String acceptUserId;

    private String msg;

    /**
     * 消息是否签收状态
        1：签收
        0：未签收
     */
    @Column(name = "sign_flag")
    private Integer signFlag;

    /**
     * 发送请求的事件
     */
    @Column(name = "create_time")
    private Date createTime;

}