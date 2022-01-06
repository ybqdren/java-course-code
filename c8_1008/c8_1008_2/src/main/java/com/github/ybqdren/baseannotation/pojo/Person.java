package com.github.ybqdren.baseannotation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;

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
    @NonNull
    private String name;
    private Integer sex;  // 1 女 2 男

    @NonNull
    private String id;

    private MyWork work;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }
}
