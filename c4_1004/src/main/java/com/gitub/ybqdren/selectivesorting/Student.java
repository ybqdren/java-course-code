package com.gitub.ybqdren.selectivesorting;


import java.util.Objects;

/**
 * Wen(Joan) Zhao <withzhaowen@126.com>
 * 2021/9/4
 * 比较自实现类
 */
public class Student implements Comparable<Student>{
    // 学号
    private String sid;
    // 姓名
    private String name;
    // 成绩
    private int score;

    public Student(String sid, String name,int score) {
        this.sid = sid;
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sid, student.sid);
    }

    @Override
    public int compareTo(Student another) {
        if(this.score < another.score){
            return -1;
        }else if(this.score == another.score){
            return 0;
        }

        return 1;

        // or
        // return this.score - another.score;
    }

    @Override
    public String toString(){
        return String.format("Student(name: %s),score:%d",name,score);
    }
}
