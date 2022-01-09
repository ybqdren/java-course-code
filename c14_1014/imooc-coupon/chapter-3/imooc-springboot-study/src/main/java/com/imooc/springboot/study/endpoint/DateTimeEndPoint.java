package com.imooc.springboot.study.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>自定义事件端点</h1>
 * Created by Qinyi.
 */
@Endpoint(id = "datetime")
public class DateTimeEndPoint {

    private String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * <h2>用来显示监控指标</h2>
     * /imooc/actuator/datetime
     * */
    @ReadOperation
    public Map<String, Object> info() {

        Map<String, Object> info = new HashMap<>();
        info.put("name", "qinyi");
        info.put("age", "19");
        info.put("datetime", new SimpleDateFormat(format).format(new Date()));

        return info;
    }

    /**
     * <h2>动态更改监控指标</h2>
     * */
    @WriteOperation
    public void setFormat(String format) {
        this.format = format;
    }
}
