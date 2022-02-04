package com.github.ybqdren.isendserver.netty;

import io.netty.channel.Channel;

import java.util.HashMap;


/**
 * <h1> netty 用户id和channel的关联关系处理 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

public class UserChannelRel {

	private static HashMap<String, Channel> manager = new HashMap<>();

	public static void put(String senderId, Channel channel) {
		manager.put(senderId, channel);
	}
	
	public static Channel get(String senderId) {
		return manager.get(senderId);
	}
	
	public static void output() {
		for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
			System.out.println("UserId: " + entry.getKey() 
							+ ", ChannelId: " + entry.getValue().id().asLongText());
		}
	}
}
