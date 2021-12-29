package com.github.ybqdre.singleton;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 12:10
 * @package com.github.ybqdre.singleton
 * @description
 **/
public class PersonMachine {
    private String name;
    private String arg;

    // 1. 懒汉模式，只创建实例对象 1 次，如果没有示例再创建
    // 2. 饿汉模式，每次调用都创建实例对象 1 次
    private static PersonMachine instance;

    // 构造器私有，外部不能实例化
    private PersonMachine() {
        System.out.println("创建了 Person 对象：" + instance.getClass());
    }

    // 提供给外部的方法
    public static PersonMachine guiguBoss(){
        return new PersonMachine();
    }
}
