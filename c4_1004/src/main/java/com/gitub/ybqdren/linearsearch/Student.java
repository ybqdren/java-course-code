package com.gitub.ybqdren.linearsearch;

import java.util.Objects;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/8/29
 */
public class Student {
    // 学号
    private String sid;
    // 姓名
    private String name;

    public Student(String sid,String name) {
        this.sid = sid;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sid, student.sid);
    }

}
