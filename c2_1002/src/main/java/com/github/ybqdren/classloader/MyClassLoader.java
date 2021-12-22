package com.github.ybqdren.classloader;

import java.io.*;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/18
 *
 * 自定义 ClassLoader
 */
public class MyClassLoader extends ClassLoader{
    private String myName = "";

    public MyClassLoader(String myName){
        this.myName = myName;
    }

    // 查找到类
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);

        // 把二进制流的数据转换成类
        return this.defineClass(name,data,0,data.length);
    }

    // 加载类
    private byte[] loadClassData(String clsName){
        byte[] data = null;

        InputStream in = null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 将包名中的 . 全部替换成 /
        clsName = clsName.replace(".","/");

        try(out) {
            // ClassLoader 去 classes/ 目录下找 .class 文件
            in = new FileInputStream(new File("target/classes/"+clsName+".class"));

            int a = 0;

            while ((a = in.read()) != -1){ out.write(a); }

            // 从读取到的 .class 文件获取二进制数据
            data = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
