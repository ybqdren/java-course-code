package com.github.ybqdren.isendserver.enums;


/**
 * <h1> 忽略或者通过 好友请求的枚举 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/


public enum OperatorFriendRequestTypeEnum {
	
	IGNORE(0, "忽略"),
	PASS(1, "通过");
	
	public final Integer type;
	public final String msg;
	
	OperatorFriendRequestTypeEnum(Integer type, String msg){
		this.type = type;
		this.msg = msg;
	}
	
	public Integer getType() {
		return type;
	}  
	
	public static String getMsgByType(Integer type) {
		for (OperatorFriendRequestTypeEnum operType : OperatorFriendRequestTypeEnum.values()) {
			if (operType.getType() == type) {
				return operType.msg;
			}
		}
		return null;
	}
	
}
