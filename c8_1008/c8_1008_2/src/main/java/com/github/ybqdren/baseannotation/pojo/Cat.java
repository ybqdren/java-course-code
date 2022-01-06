package com.github.ybqdren.baseannotation.pojo;

import lombok.NonNull;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/7 6:52
 * @package com.github.ybqdren.baseannotation.pojo
 * @description
 **/
public class Cat {
    @NonNull
    private String nikaName;
    private Integer sex;
    private Integer years;

    public Cat() {
    }

    public Cat(String nikaName) {
        System.out.println("正在初始化一只小猫咪");
        this.nikaName = nikaName;
    }

    public String getNikaName() {
        return nikaName;
    }

    public void setNikaName(String nikaName) {
        this.nikaName = nikaName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }


    @PostConstruct
    public void wakeUp(){
        System.out.println("猫咪起床了");
    }

    public void huhuhu(){
        System.out.println(this.nikaName + "正在呼呼呼 ~");
    }


    @PreDestroy
    public void fallAsleep(){
        System.out.println("猫咪睡觉了");
    }

    public void destroy() {
        System.out.println("cat destroy 方法执行");
    }
}
