package org.github.ybqdren.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/12/12
 */
public class Generator {
    public static void main(String[] args) throws IOException {
        // 1. 读入类
        ClassReader cr = new ClassReader("org/github/ybqdren/asm/TestClass");   // 要加载类的路径

        // 2. 输出对象
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        // 3.使用ClassVisitor
        ClassVisitor cv = new MyClassVistor(cw);

        // 4. 执行
        // ClassReader.SKIP_DEBUG 跳过调试
        cr.accept(cv,ClassReader.SKIP_DEBUG);

        byte[] data = cw.toByteArray();

        // 输出
        File f = new File("target/classes/org/github/ybqdren/asm/TestClass.class");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(data);
        fos.close();
    }
}
