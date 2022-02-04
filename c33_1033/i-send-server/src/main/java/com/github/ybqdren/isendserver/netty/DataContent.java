package com.github.ybqdren.isendserver.netty;

import java.io.Serializable;

/**
 * <h1> netty 用户消息详情 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

public class DataContent implements Serializable {

	private static final long serialVersionUID = 8021381444738260454L;

	private Integer action;		// 动作类型
	private ChatMsg chatMsg;	// 用户的聊天内容entity
	private String extand;		// 扩展字段
	
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public ChatMsg getChatMsg() {
		return chatMsg;
	}
	public void setChatMsg(ChatMsg chatMsg) {
		this.chatMsg = chatMsg;
	}
	public String getExtand() {
		return extand;
	}
	public void setExtand(String extand) {
		this.extand = extand;
	}
}
