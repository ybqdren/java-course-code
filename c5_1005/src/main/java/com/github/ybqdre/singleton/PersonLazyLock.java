package com.github.ybqdre.singleton;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/24 12:10
 * @package com.github.ybqdre.singleton
 * @description
 * 存在问题：多线程中 多个请求同时请求到 guiguBoss()
 **/
public class PersonLazyLock {
    private String name;
    private String arg;

    // 1. 懒汉模式，只创建实例对象 1 次，如果没有示例再创建
    // 2. 饿汉模式，每次调用都创建实例对象 1 次
    private volatile static PersonLazyLock instance;

    // 构造器私有，外部不能实例化
    private PersonLazyLock() {
        System.out.println("创建了 Person 对象：" + instance.getClass());
    }

    // 提供给外部的方法
    // 加上 synchronized 关键字，保证数据操作是一致的
    // 方式：
    // 1. public static synchronized PersonLazyLock guiguBoss() 锁太大
    // 锁太大，会导致效率很低
    // 2. 和第一个一样，锁还是太大了
    //         synchronized (PersonLazyLock.class){
    //            // 如果没有实例才创建
    //            if(instance == null){
    //                PersonLazyLock person = new PersonLazyLock();
    //                instance = person;
    //            }
    //        }
    // 3.双重检查锁+内存可见性
    //             if(instance == null){
    //                synchronized (PersonLazyLock.class) {
    //                    if (instance == null) {
    //                        PersonLazyLock person = new PersonLazyLock();
    //                        instance = person;
    //                    }
    //                }
    //            }
    // 赋值操作会先创建对象，再分配地址          PersonLazyLock person = new PersonLazyLock();
    // 所以需要给对象变量加上内存可见性         private volatile static PersonLazyLock instance;
    public static PersonLazyLock guiguBoss(){
            // 如果没有实例才创建
            if(instance == null){
                synchronized (PersonLazyLock.class) {
                    if (instance == null) {
                        PersonLazyLock person = new PersonLazyLock();
                        instance = person;
                    }
                }
            }
        return null;
    }
}
