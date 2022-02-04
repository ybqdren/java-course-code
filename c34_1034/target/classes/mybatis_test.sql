
- ------------------------------------------------------------------------
-- Table structure for ay_user
- -----------------------------------------------------------------------
DROP TABLE IF EXISTS `ay_user`;

CREATE TABLE `ay_user`(
    `id` bigint(32) NOT NULL AUTO_INCREMENT,
    `name` varchar(10) DEFAULT NULL,
    `password` varchar(64) DEFAULT NULL,
    PRIMARY  KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 2 DEFAULT CHARSET=utf8;


INSERT INTO `ay_user`(`name` , `password`)
    VALUES
        ("zhao wen" , "123") ,
        ("zhang san" , "123") ,
        ("li si" , "123") ,
        ("wang wu" , "123");