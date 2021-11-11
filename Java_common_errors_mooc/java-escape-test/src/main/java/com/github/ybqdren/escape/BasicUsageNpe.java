package com.github.ybqdren.escape;

import java.util.ArrayList;
import java.util.List;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/11/6
 * <h1>字符串 数组 集合在使用时使用空指针</h1>
 */
public class BasicUsageNpe {
    private static boolean StringEquals(String x,String y){
        return x.equals(y);
    }

    public static class User{
        public String name;
    }

    public static void main(String[] args){
        // 1.字符串使用 equals 可能会报空指针错误
/*        System.out.println(StringEquals("xyz",null));
        System.out.println(StringEquals(null,"xyz"));*/

        // 2.对象数组new出来，但是元素没有初始化
/*        User[] users = new User[10];
        for(int i=0;i != 10;i++){
//            users[i] = new User();
            users[i].name = "ybqdren-"+i;
        }*/

        // 3.List对象 addAll 传递null会抛出空指针
        List<User> users = new ArrayList<User>();
        User user = null;
        List<User> users_ = null;
        users.add(user);
        users.addAll(users_);
    }
}
