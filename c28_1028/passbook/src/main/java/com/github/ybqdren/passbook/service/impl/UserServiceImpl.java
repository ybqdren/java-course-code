package com.github.ybqdren.passbook.service.impl;

import com.github.ybqdren.passbook.constant.Constants;
import com.github.ybqdren.passbook.service.IUserService;
import com.github.ybqdren.passbook.vo.Response;
import com.github.ybqdren.passbook.vo.User;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 创建用户服务实现 </h1>
 **/

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    /** HBase 客户端 */
    private HbaseTemplate hbaseTemplate;

    /** Redis 客户端*/
    private StringRedisTemplate redisTemplate;

    @Autowired
    public UserServiceImpl(HbaseTemplate hbaseTemplate, StringRedisTemplate redisTemplate) {
        this.hbaseTemplate = hbaseTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Response createUser(User user) throws Exception {
        byte[] FAMIL_B = Constants.UserTable.FAMILY_B.getBytes();
        byte[] NAME = Constants.UserTable.NAME.getBytes();
        byte[] AGE = Constants.UserTable.AGE.getBytes();
        byte[] SEX = Constants.UserTable.SEX.getBytes();

        byte[] FAMILY_O = Constants.UserTable.FAMILY_O.getBytes();
        byte[] PHONE = Constants.UserTable.PHONE.getBytes();
        byte[] ADDRESS = Constants.UserTable.ADDRESS.getBytes();

        Long curCount = redisTemplate.opsForValue().increment(Constants.USE_COUNT_REDIS_KEY);
        Long userId = getUserId(curCount);

        List<Mutation> datas = new ArrayList<>();

        // 将数据填充到对应的列族中
        Put put = new Put(Bytes.toBytes(userId));

        put.addColumn(FAMIL_B , NAME , Bytes.toBytes(user.getBaseInfo().getName()));
        put.addColumn(FAMIL_B , AGE , Bytes.toBytes(user.getBaseInfo().getAge()));
        put.addColumn(FAMIL_B , SEX , Bytes.toBytes(user.getBaseInfo().getSex()));

        put.addColumn(FAMILY_O , PHONE , Bytes.toBytes(user.getOtherInfo().getPhone()));
        put.addColumn(FAMILY_O , ADDRESS , Bytes.toBytes(user.getOtherInfo().getAdress()));

        // 将列族保持更新
        datas.add(put);
        hbaseTemplate.saveOrUpdates(Constants.UserTable.TABLE_NAME , datas);

        // 将操作状态返回
        user.setId(userId);
        return new Response(user);
    }

    /**
     * <h2> 使用 redis 生成 userId </h2>
     * @param prefix 当前用户数
     * @return 用户  id
     */
    private static Long getUserId(Long prefix){
        String suffix = RandomStringUtils.randomNumeric(5);
        return Long.valueOf(prefix + suffix);
    }
}
