package org.github.ybqdren.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.github.ybqdren.common.enumeration.UserLevel;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/4 14:09
 * @package org.github.ybqdren.common.bean
 * @description
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MetaInfo {
    private String permission;
    private String module;
    private String identity;
    private UserLevel userLevel;
}
