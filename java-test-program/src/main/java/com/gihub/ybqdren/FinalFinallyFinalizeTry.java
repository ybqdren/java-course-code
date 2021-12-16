package com.gihub.ybqdren;

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
}
