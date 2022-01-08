package com.github.ybqdren.baseannotation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/8 9:53
 * @package com.github.ybqdren.baseannotation.pojo
 * @description
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String name;

    private String sex;

    private String work;
}
