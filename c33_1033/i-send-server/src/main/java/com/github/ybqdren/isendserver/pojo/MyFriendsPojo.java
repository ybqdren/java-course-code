package com.github.ybqdren.isendserver.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <h1> pojo 用户与其好友的信息记录 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/


@Data
@Table(name = "my_friends")
public class MyFriendsPojo {
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "my_user_id")
    private String myUserId;

    /**
     * 用户的好友id
     */
    @Column(name = "my_friend_user_id")
    private String myFriendUserId;

}