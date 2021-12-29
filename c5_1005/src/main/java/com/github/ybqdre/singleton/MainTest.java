package com.github.ybqdre.singleton;

import java.util.Map;
import java.util.Properties;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 12:07
 * @package com.github.ybqdre.singleton
 * @description
 **/
public class MainTest {
    public static void main(String[] args) {
        // 系统信息
        Properties prop = System.getProperties();
        System.out.println(prop);

        // 环境变量
        Map<String,String> env = System.getenv();
        System.out.println(env);
    }
}
