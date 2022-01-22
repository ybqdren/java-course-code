package com.github.ybqdren.passbook.mapper;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.vo.User;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> HBase User Row To User Object </h1>
 **/
public class UserRowMapper implements RowMapper<User> {

    private static byte[] FAMILY_B = Constants.UserTable.FAMILY_B.getBytes();
    private static byte[] NAME = Constants.UserTable.NAME.getBytes();
    private static byte[] AGE = Constants.UserTable.AGE.getBytes();
    private static byte[] SEX = Constants.UserTable.SEX.getBytes();

    private static byte[] FAMILY_0 = Constants.UserTable.FAMILY_O.getBytes();
    private static byte[] PHONE = Constants.UserTable.PHONE.getBytes();
    private static byte[] ADDRESS = Constants.UserTable.ADDRESS.getBytes();


    /** 将 hbase 字节数组映射为 Java 对象 */
    @Override
    public User mapRow(Result result, int i) throws Exception {

        /** 将字节数组转换为 Java 的对象 */
        User.BaseInfo baseInfo = new User.BaseInfo(
                Bytes.toString(result.getValue(FAMILY_B,NAME)),
                Bytes.toInt(result.getValue(FAMILY_B,AGE)),
                Bytes.toString(result.getValue(FAMILY_B,SEX))
        );

        User.OtherInfo otherInfo = new User.OtherInfo(
                Bytes.toString(result.getValue(FAMILY_0,PHONE)),
                Bytes.toString(result.getValue(FAMILY_0,ADDRESS))
        );

        return new User(
                Bytes.toLong(result.getRow()) , baseInfo , otherInfo
        );
    }
}
