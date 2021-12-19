package org.github.ybqdren.classloader;

import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/18
 */
public class ClassLoaderStudy {
    public static void main(String[] args) throws ClassNotFoundException {
/*        // 启动类加载
        String str = "Hello Class Loader";
        System.out.println("str class loader=="+str.getClass().getClassLoader());  // 启动类加载器

        // 平台类加载器
//        Class driver = Class.forName("java.sql.Driver");   // 通过模块名称加载类
//        System.out.println("driver class loader =="+driver.getClassLoader()); // driver 本来就是class，所以不需要在 getClass

        // 应用程序类加载器
        ClassLoaderStudy t = new ClassLoaderStudy();
        System.out.println("t class loader=="+t.getClass().getClassLoader());  // 启动类加载器
//        System.out.println("str class loader=="+str.getClass().getClassLoader().getParent());  // 启动类加载器*/



        // 使用自定义的 ClassLoader
        MyClassLoader myClassLoader = new MyClassLoader("MyClassloader");
        Class cls1 = myClassLoader.loadClass("org.github.ybqdren.classloader.MyClass");

        // cls1 class loader ==jdk.internal.loader.ClassLoaders$AppClassLoader@2437c6dc
        System.out.println("cls1 class loader =="+ cls1.getClassLoader());

        //cls1 parent class loader ==jdk.internal.loader.ClassLoaders$PlatformClassLoader@224edc67
        System.out.println("cls1 parent class loader =="+ cls1.getClassLoader().getParent());

        // java.sql.Array class loader==jdk.internal.loader.ClassLoaders$PlatformClassLoader@224edc67
        System.out.println("java.sql.Array class loader=="+Driver.class.getClassLoader());

        // java.sql.Array class loader==jdk.internal.loader.ClassLoaders$PlatformClassLoader@224edc67
        Class cls2 = Class.forName("java.sql.Driver");
        System.out.println("java.sql.Array class loader=="+Driver.class.getClassLoader());

    }
}
