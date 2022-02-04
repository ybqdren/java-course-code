package com.github.ybqdren.isendserver.dao;


import com.github.ybqdren.isendserver.pojo.Users;
import com.github.ybqdren.isendserver.pojo.vo.FriendRequestVO;
import com.github.ybqdren.isendserver.pojo.vo.MyFriendsVO;
import com.github.ybqdren.isendserver.utils.MyMapper;

import java.util.List;

public interface UsersMapperCustom extends MyMapper<Users> {
	
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);
	
	public List<MyFriendsVO> queryMyFriends(String userId);
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}