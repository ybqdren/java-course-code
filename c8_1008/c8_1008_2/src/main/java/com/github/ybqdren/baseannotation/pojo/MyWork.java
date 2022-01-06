package com.github.ybqdren.baseannotation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 14:41
 * @package com.github.ybqdren.baseannotation.pojo
 * @description
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyWork {
    private String companyName;
    private String workName;
    private int salary;
    private int workHours;
}
