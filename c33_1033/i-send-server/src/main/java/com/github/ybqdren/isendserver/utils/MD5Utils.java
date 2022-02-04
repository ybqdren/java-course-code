package com.github.ybqdren.isendserver.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * <h1> MD5 编码解码工具类 </h1>
 *
 * @author zhao wen
 * @since 0.0.1
 **/

public class MD5Utils {

	/**
	 * @Description: 对字符串进行md5加密 
	 */
	public static String getMD5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		return newstr;
	}

	public static void main(String[] args) {
		try {
			String md5 = getMD5Str("imooc");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
