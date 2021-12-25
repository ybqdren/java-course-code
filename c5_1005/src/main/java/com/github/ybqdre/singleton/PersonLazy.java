package com.github.ybqdre.singleton;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 12:10
 * @package com.github.ybqdre.singleton
 * @description
 * 存在问题：多线程中 多个请求同时请求到 guiguBoss()
 **/
public class PersonLazy {
    private String name;
    private String arg;

    // 1. 懒汉模式，只创建实例对象 1 次，如果没有示例再创建
    // 2. 饿汉模式，每次调用都创建实例对象 1 次
    private static PersonLazy instance;

    // 构造器私有，外部不能实例化
    private PersonLazy() {
        System.out.println("创建了 Person 对象：" + instance.getClass());
    }

    // 提供给外部的方法
    public static PersonLazy guiguBoss(){
        // 如果没有实例才创建
        if(instance == null){
            PersonLazy person = new PersonLazy();
            instance = person;
        }
        return null;
    }
}
