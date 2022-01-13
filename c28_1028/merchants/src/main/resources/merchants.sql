CREATE TABLE `merchants`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(64) COLLATE utf8_bin NOT NULL COMMEN '商户名称',
    `logo_url` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '商户营业执照',
    `phone` varchat(64) COLLATE utf8_bin NOT NULL COMMENT '商户地址',
    `address` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户地址',
    `is_audit` BOOLEAN NOT NULL COMMENT '是否通过审核',                              -- 是否通过审核使用布尔值
    PRIMARY KEY(`id`)                                                              -- 主键
)ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARACTER=utf8;                           -- ENGINE：InnoDB 引擎中包含索引，可以使用 B 树
                                                                                   -- AUTO_INCREMENT：默认的自增索引是从1开始，此处我们设置开始至为17
                                                                                   -- CHARACTER：字符编码集为 utf-8