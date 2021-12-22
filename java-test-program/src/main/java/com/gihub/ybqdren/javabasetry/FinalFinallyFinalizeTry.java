package com.gihub.ybqdren.javabasetry;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @version v1.0
 * @description  谈谈 final 和 finally finalize 有什么不同
 **/
public class FinalFinallyFinalizeTry {
    public static void main(String[] args) {

    }


    // 1. finaly 并不等同于 immutable
    private void test1(){
        final List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("World");
//        List<String> unmodifiableStrList = List.of("Hello","world");    // List.of jdk 9 新增
//        unmodifiableStrList.add("again");
    }

    // try-with-resource  java-9 引入
/*    private void tryWithResourceTest(String fileName,String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        try (writer) {
            writer.write(str); // do something with the file we've opened
        }
        catch(IOException e) {
            // handle the exception
        }
    }*/
}
