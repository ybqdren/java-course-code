package com.github.ybqdren.baseannotation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:17
 * @package com.github.ybqdren.baseannotation.pojo
 * @description
 **/


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    private String name;
    private Integer sex;  // 1 女 2 男
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
