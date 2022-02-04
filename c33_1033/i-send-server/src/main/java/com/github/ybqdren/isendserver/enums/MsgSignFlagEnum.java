package com.github.ybqdren.isendserver.enums;


/**
 * <h1> 消息签收状态 枚举 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

public enum MsgSignFlagEnum {
	
	unsign(0, "未签收"),
	signed(1, "已签收");	
	
	public final Integer type;
	public final String content;
	
	MsgSignFlagEnum(Integer type, String content){
		this.type = type;
		this.content = content;
	}
	
	public Integer getType() {
		return type;
	}  
}
