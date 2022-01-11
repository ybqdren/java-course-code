package com.example.emos.api.config.tencent;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.zip.Deflater;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * 腾讯云视频推流工具类，整个过程需要结合前后端一起完成
 *
 * TRTC
 *      1. TrtcClient
 *          - AppId
 *          - UserId
 *          - 用户签名
 *              --> 由后端程序生成用户签名
 *              --> 防止其他人伪造用户签名
 *      2. 网络会议室
 *          - 工作流项目的定时器
 *                 --> 自动生成网络会议室
 *                 --> 自动销毁过期的网络会议室
 *          - 会议开始前 15 分钟会生成 RoomId，缓存到 Redis 里面
 *
 *      3. 流对象
 *          - 本地流
 *              --> 视频流
 *              --> 共享流
 *          - 远端流
 *
 *      4. 订阅事件
 *          - stream-added
 *              --> 新增远端流事件
 *              --> 远端参会人进入会议室触发
 *
 *          - stream-subscribed
 *              --> 订阅远端成功事件
 *              --> 不订阅远端流就接收不到远端的视频讯号
 *
 *          - stream-removed
 *              --> 远端流删除事件
 *              --> 远端参会人退出会议室触发
 *
 *          - audio-volume
 *              --> 参会人语音事件
 *              --> 无论本地还是远端参会人，只要说话就会触发事件
 *
 * 页面
 *      1.视频墙
 *          - 本地参会人
 *              --> 只能小屏显示
 *              --> 可以共享屏幕
 *
 *          - 远程参会人
 *              --> 小屏显示
 *              --> 大屏显示
 *
 *      2.在线参会人列表
 *         - 参会人的部门和姓名
 *         - 说话语音的音量大小
 *
 *
 *      3. 操作按钮
 *          - 电话图标
 *          - 摄像头图标
 *          - 麦克风图标
 *          - 共享屏幕图标
 */

@Component
public class TrtcUtil {

    @Value("${tencent.trtc.appId}")
    private int appId;

    @Value("${tencent.trtc.expire}")
    private int expire;

    @Value("${tencent.trtc.secretKey}")
    private String secretKey;


    public String genUserSig(String userId) {
        return GenTLSSignature(appId, userId, expire, null, secretKey);
    }

    private String GenTLSSignature(long sdkappid, String userId, long expire, byte[] userbuf, String priKeyContent) {
        if (StrUtil.isEmpty(priKeyContent)) {
            return "";
        }
        long currTime = System.currentTimeMillis() / 1000;
        JSONObject sigDoc = new JSONObject();
        sigDoc.set("TLS.ver", "2.0");
        sigDoc.set("TLS.identifier", userId);
        sigDoc.set("TLS.sdkappid", sdkappid);
        sigDoc.set("TLS.expire", expire);
        sigDoc.set("TLS.time", currTime);

        String base64UserBuf = null;
        if (null != userbuf) {
            base64UserBuf = Base64.encode(userbuf);
            sigDoc.set("TLS.userbuf", base64UserBuf);
        }
        String sig = hmacsha256(sdkappid, userId, currTime, expire, priKeyContent, base64UserBuf);
        if (sig.length() == 0) {
            return "";
        }
        sigDoc.set("TLS.sig", sig);

        Deflater compressor = new Deflater();
        compressor.setInput(sigDoc.toString().getBytes(Charset.forName("UTF-8")));
        compressor.finish();
        byte[] compressedBytes = new byte[2048];
        int compressedBytesLength = compressor.deflate(compressedBytes);
        compressor.end();
        return new String(base64EncodeUrl(Arrays.copyOfRange(compressedBytes, 0, compressedBytesLength)));
    }


    private static String hmacsha256(long sdkappid, String userId, long currTime, long expire, String priKeyContent, String base64Userbuf) {
        String contentToBeSigned = "TLS.identifier:" + userId + "\n"
                + "TLS.sdkappid:" + sdkappid + "\n"
                + "TLS.time:" + currTime + "\n"
                + "TLS.expire:" + expire + "\n";
        if (null != base64Userbuf) {
            contentToBeSigned += "TLS.userbuf:" + base64Userbuf + "\n";
        }
        try {
            byte[] byteKey = priKeyContent.getBytes("UTF-8");
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, "HmacSHA256");
            hmac.init(keySpec);
            byte[] byteSig = hmac.doFinal(contentToBeSigned.getBytes("UTF-8"));
            return Base64.encode(byteSig);
        } catch (UnsupportedEncodingException e) {
            return "";
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (InvalidKeyException e) {
            return "";
        }
    }

    private static byte[] base64EncodeUrl(byte[] input) {
        byte[] base64 = Base64.encode(input).getBytes();
        for (int i = 0; i < base64.length; ++i)
            switch (base64[i]) {
                case '+':
                    base64[i] = '*';
                    break;
                case '/':
                    base64[i] = '-';
                    break;
                case '=':
                    base64[i] = '_';
                    break;
                default:
                    break;
            }
        return base64;
    }

}