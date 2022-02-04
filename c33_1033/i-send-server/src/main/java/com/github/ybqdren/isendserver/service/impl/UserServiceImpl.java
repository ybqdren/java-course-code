package com.github.ybqdren.isendserver.service.impl;

import com.github.ybqdren.isendserver.dao.*;
import com.github.ybqdren.isendserver.enums.MsgActionEnum;
import com.github.ybqdren.isendserver.enums.MsgSignFlagEnum;
import com.github.ybqdren.isendserver.enums.SearchFriendsStatusEnum;
import com.github.ybqdren.isendserver.netty.DataContent;
import com.github.ybqdren.isendserver.netty.UserChannelRel;
import com.github.ybqdren.isendserver.netty.ChatMsg;
import com.github.ybqdren.isendserver.pojo.ChatMsgPojo;
import com.github.ybqdren.isendserver.pojo.FriendsRequestPojo;
import com.github.ybqdren.isendserver.pojo.MyFriendsPojo;
import com.github.ybqdren.isendserver.pojo.UsersPojo;
import com.github.ybqdren.isendserver.pojo.vo.FriendRequestVO;
import com.github.ybqdren.isendserver.pojo.vo.MyFriendsVO;
import com.github.ybqdren.isendserver.service.UserService;
import com.github.ybqdren.isendserver.utils.FastDFSClient;
import com.github.ybqdren.isendserver.utils.FileUtils;
import com.github.ybqdren.isendserver.utils.JsonUtils;
import com.github.ybqdren.isendserver.utils.QRCodeUtils;
import com.github.ybqdren.org.n3r.idworker.Sid;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * <h1> service 用户服务 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

@Service
public class UserServiceImpl implements UserService {

	private static Sid sid = new Sid();

	@Autowired
	private UsersMapper userMapper;
	
	@Autowired
	private UsersMapperCustom usersMapperCustom;
	
	@Autowired
	private MyFriendsMapper myFriendsMapper;
	
	@Autowired
	private FriendsRequestMapper friendsRequestMapper;
	
	@Autowired
	private ChatMsgMapper chatMsgMapper;
	
	@Autowired
	private QRCodeUtils qrCodeUtils;
	
	@Autowired
	private FastDFSClient fastDFSClient;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
		
		UsersPojo user = new UsersPojo();
		user.setUsername(username);
		
		UsersPojo result = userMapper.selectOne(user);
		
		return result != null ? true : false;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public UsersPojo queryUserForLogin(String username, String pwd) {
		
		Example userExample = new Example(UsersPojo.class);
		Criteria criteria = userExample.createCriteria();
		
		criteria.andEqualTo("username", username);
		criteria.andEqualTo("password", pwd);
		
		UsersPojo result = userMapper.selectOneByExample(userExample);
		
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public UsersPojo saveUser(UsersPojo user) {
		
		String userId = sid.nextShort();
		
		// 为每个用户生成一个唯一的二维码
		String qrCodePath = "C://user" + userId + "qrcode.png";
		// muxin_qrcode:[username]
		qrCodeUtils.createQRCode(qrCodePath, "muxin_qrcode:" + user.getUsername());
		MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);
		
		String qrCodeUrl = "";
		try {
			qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		user.setQrcode(qrCodeUrl);
		
		user.setId(userId);
		userMapper.insert(user);
		
		return user;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public UsersPojo updateUserInfo(UsersPojo user) {
		userMapper.updateByPrimaryKeySelective(user);
		return queryUserById(user.getId());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public UsersPojo queryUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Integer preconditionSearchFriends(String myUserId, String friendUsername) {

		UsersPojo user = queryUserInfoByUsername(friendUsername);
		
		// 1. 搜索的用户如果不存在，返回[无此用户]
		if (user == null) {
			return SearchFriendsStatusEnum.USER_NOT_EXIST.status;
		}
		
		// 2. 搜索账号是你自己，返回[不能添加自己]
		if (user.getId().equals(myUserId)) {
			return SearchFriendsStatusEnum.NOT_YOURSELF.status;
		}
		
		// 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
		Example mfe = new Example(MyFriendsPojo.class);
		Criteria mfc = mfe.createCriteria();
		mfc.andEqualTo("myUserId", myUserId);
		mfc.andEqualTo("myFriendUserId", user.getId());
		MyFriendsPojo myFriendsPojoRel = myFriendsMapper.selectOneByExample(mfe);
		if (myFriendsPojoRel != null) {
			return SearchFriendsStatusEnum.ALREADY_FRIENDS.status;
		}
		
		return SearchFriendsStatusEnum.SUCCESS.status;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public UsersPojo queryUserInfoByUsername(String username) {
		Example ue = new Example(UsersPojo.class);
		Criteria uc = ue.createCriteria();
		uc.andEqualTo("username", username);
		return userMapper.selectOneByExample(ue);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void sendFriendRequest(String myUserId, String friendUsername) {
		
		// 根据用户名把朋友信息查询出来
		UsersPojo friend = queryUserInfoByUsername(friendUsername);
		
		// 1. 查询发送好友请求记录表
		Example fre = new Example(FriendsRequestPojo.class);
		Criteria frc = fre.createCriteria();
		frc.andEqualTo("sendUserId", myUserId);
		frc.andEqualTo("acceptUserId", friend.getId());
		FriendsRequestPojo friendRequest = friendsRequestMapper.selectOneByExample(fre);
		if (friendRequest == null) {
			// 2. 如果不是你的好友，并且好友记录没有添加，则新增好友请求记录
			String requestId = sid.nextShort();
			
			FriendsRequestPojo request = new FriendsRequestPojo();
			request.setId(requestId);
			request.setSendUserId(myUserId);
			request.setAcceptUserId(friend.getId());
			request.setRequestDateTime(new Date());
			friendsRequestMapper.insert(request);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<FriendRequestVO> queryFriendRequestList(String acceptUserId) {
		return usersMapperCustom.queryFriendRequestList(acceptUserId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteFriendRequest(String sendUserId, String acceptUserId) {
		Example fre = new Example(FriendsRequestPojo.class);
		Criteria frc = fre.createCriteria();
		frc.andEqualTo("sendUserId", sendUserId);
		frc.andEqualTo("acceptUserId", acceptUserId);
		friendsRequestMapper.deleteByExample(fre);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void passFriendRequest(String sendUserId, String acceptUserId) {
		saveFriends(sendUserId, acceptUserId);
		saveFriends(acceptUserId, sendUserId);
		deleteFriendRequest(sendUserId, acceptUserId);
		
		Channel sendChannel = UserChannelRel.get(sendUserId);
		if (sendChannel != null) {
			// 使用websocket主动推送消息到请求发起者，更新他的通讯录列表为最新
			DataContent dataContent = new DataContent();
			dataContent.setAction(MsgActionEnum.PULL_FRIEND.type);
			
			sendChannel.writeAndFlush(
					new TextWebSocketFrame(
							JsonUtils.objectToJson(dataContent)));
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveFriends(String sendUserId, String acceptUserId) {
		MyFriendsPojo myFriendsPojo = new MyFriendsPojo();
		String recordId = sid.nextShort();
		myFriendsPojo.setId(recordId);
		myFriendsPojo.setMyFriendUserId(acceptUserId);
		myFriendsPojo.setMyUserId(sendUserId);
		myFriendsMapper.insert(myFriendsPojo);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<MyFriendsVO> queryMyFriends(String userId) {
		List<MyFriendsVO> myFirends = usersMapperCustom.queryMyFriends(userId);
		return myFirends;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String saveMsg(ChatMsg chatMsg) {
		
		ChatMsgPojo msgDB = new ChatMsgPojo();
		String msgId = sid.nextShort();
		msgDB.setId(msgId);
		msgDB.setAcceptUserId(chatMsg.getReceiverId());
		msgDB.setSendUserId(chatMsg.getSenderId());
		msgDB.setCreateTime(new Date());
		msgDB.setSignFlag(MsgSignFlagEnum.unsign.type);
		msgDB.setMsg(chatMsg.getMsg());
		
		chatMsgMapper.insert(msgDB);
		
		return msgId;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateMsgSigned(List<String> msgIdList) {
		usersMapperCustom.batchUpdateMsgSigned(msgIdList);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<ChatMsgPojo> getUnReadMsgList(String acceptUserId) {
		
		Example chatExample = new Example(ChatMsg.class);
		Criteria chatCriteria = chatExample.createCriteria();
		chatCriteria.andEqualTo("signFlag", 0);
		chatCriteria.andEqualTo("acceptUserId", acceptUserId);
		
		List<ChatMsgPojo> result = chatMsgMapper.selectByExample(chatExample);
		
		return result;
	}
}
