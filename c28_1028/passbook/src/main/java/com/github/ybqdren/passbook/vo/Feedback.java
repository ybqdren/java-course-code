package com.github.ybqdren.passbook.vo;

import com.github.ybqdren.passbook.constant.FeedbackType;
import com.google.common.base.Enums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    /** 用户 id */
    private Long userId;

    /** 评论类型 */
    private String type;

    /** PassTemplate RowKey ，如果是 app 类型的品论，则没有 */
    private String templateId;

    /** 评论内容 */
    private String comment;

    public boolean validte(){
        // ...
        FeedbackType feedbackType = Enums.getIfPresent(
                FeedbackType.class, this.type.toUpperCase()
        ).orNull();

        return !(null == feedbackType || null == comment);
    }
}
