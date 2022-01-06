package com.github.ybqdren.baseannotation.service;

import com.github.ybqdren.baseannotation.pojo.MyWork;
import com.github.ybqdren.baseannotation.pojo.Person;

import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:20
 * @package com.github.ybqdren.baseannotation.service
 * @description
 **/


public interface InfoService {
    // 随便获取一个人
    public Optional<Person> getAPersonByRange();

    // 找一份工作糊口
    public Optional<MyWork> getAJob();

    // 获取一个单例对象人 直接返回
    public Optional<Person> getOneSinglePerson();

    // 获取一个单例对象人 有赋值操作
    public Optional<Person> getOneSinglePersonHaveValue();
}
