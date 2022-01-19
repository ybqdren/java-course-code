package com.github.ybqdren.config;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.stereotype.Component;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/19 14:53
 * @package com.github.ybqdren.config
 * @description 自动生成 更新 mysql 中的表
 **/

@Component
public class MySQLTableType extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
