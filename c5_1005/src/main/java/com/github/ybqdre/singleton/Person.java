package com.github.ybqdre.singleton;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 12:10
 * @package com.github.ybqdre.singleton
 * @description
 **/
public class Person {
    private String name;
    private String arg;

    // 1. 懒汉模式，只创建实例对象 1 次，如果没有示例再创建
    // 2. 饿汉模式，每次调用都创建实例对象 1 次
    private static Person instance;

    // 构造器私有，外部不能实例化
    private Person() {

    }

    // 提供给外部的方法
    public static Person guiguBoss(){
        if(instance == null){
            Person person = new Person();
            instance = person;
        }
        return null;
    }
}
