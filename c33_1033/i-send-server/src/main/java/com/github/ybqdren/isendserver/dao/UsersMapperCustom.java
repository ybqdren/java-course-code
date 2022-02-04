package com.github.ybqdren.isendserver.dao;


import com.github.ybqdren.isendserver.pojo.UsersPojo;
import com.github.ybqdren.isendserver.pojo.vo.FriendRequestVO;
import com.github.ybqdren.isendserver.pojo.vo.MyFriendsVO;
import com.github.ybqdren.isendserver.utils.MyMapper;

import java.util.List;

/**
 * @author zhao wen
 * @since 0.0.1
 **/

public interface UsersMapperCustom extends MyMapper<UsersPojo> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}