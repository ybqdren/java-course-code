package com.github.ybqdren.passbook.constant;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> 常量定义 </h1>
 **/
public class Constants {
    /** 商户优惠卷 Kafka Topic */
    public static final String TEMPLATE_TOPIC = "merchants-template";

    /** token 文件存储目录 */
    public static final String TOKEN_DIR = "/tmp/token";

    /** 已使用的 token 文件名后缀 */
    public static final String USED_TKEN_SUFFIX = "_";

    /** 用户数的 redis key   用来和用户组数成关系 */
    public static final String USE_COUNT_REDIS_KEY = "immoc-user-count";

    /**
     * <h2> User HBase Table </h2>
     */
    public class UserTable{
        /** User HBase 表名 */
        public static final String TABLE_NAME = "pb:user";

        /** 基本信息列族 */
        public static final String FAMILY_B = "b";

        /** 用户名 */
        public static final String NAME = "name";

        /** 用户年龄 */
        public static final String AGE = "age";

        /** 用户性别 */
        public static final String SEX = "sex";

        /** 额外信息列族 */
        public static final String FAMILY_O = "o";

        /** 电话号码 */
        public static final String PHONE = "phone";

        /** 住址 */
        public static final String ADDRESS = "address";

    }


    /**
     * <h2> PassTemplate HBase Table </h2>
     */
    public class PassTemplateTable{
        /** PassTemplate HBase 表名 */
        public static final String TABLE_NAME = "pb:passtemplate";

        /** 基本信息列族 */
        public static final String FAMILY_B = "b";

        /** 商户 id */
        public static final String ID = "id";

        /** 优惠卷标题 */
        public static final String TITLE = "title";

        /** 优惠劵摘要信息 */
        public static final String SUMMARY = "summary";

        /** 优惠卷详细信息 */
        public static final String DESC = "desc";

        /** 优惠劵是否有 token */
        public static final String HAS_TOKEN = "has_token";

        /** 优惠卷背景色 */
        public static final String BACKGROUND = "background";

        /** 优惠卷信息列族 */
        public static final String FAMILY_C = "c";

        /** 最大个数限制 */
        public static final String LIMIT = "limit";

        /** 优惠卷开始时间 */
        public static final String START = "start";

        /** 优惠卷结束时间 */
        public static final String END = "end";
    }

    /**
     * <h2> Pass HBase Table </h2>
     */
    public class PassTable{
        /** Pass HBase 表名 */
        public static final String TABLE_NAME = "pb:pass";

        /** 信息列族 */
        public static final String FAMILY_I = "i";

        /** 用户 id */
        public static final String USER_ID = "user_id";

        /** 优惠卷 id  */
        public static final String TEMPLATE_ID = "template_id";

        /** 优惠劵识别码 */
        public static final String TOKEN = "token";

        /** 领取日期 */
        public static final String ASSIGNED_DATE = "assigned_date";

        /** 消费日期 */
        public static final String CON_DATE = "con_date";

    }

    /**
     * <h2> Feedback Hbase Table </h2>
     */
    public class Feedback{
        /** Feedback HBase 表名 */
        public static final String TABLE_NAME = "pb:feedback";

        /** 信息列族 */
        public static final String FAMILY_I = "i";

        /** 用户 id */
        public static final String USER_ID = "user_id";

        /** 评论类型  */
        public static final String TYPE = "type";

        /** PassTemplate RowKey，如果是 app 评论，则是 -1 */
        public static final String TEMPLATE_ID = "template_id";

        /** 评论内容 */
        public static final String COMMENT = "comment";
    }
}
