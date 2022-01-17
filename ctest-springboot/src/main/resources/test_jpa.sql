CREATE TABLE `user`(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    `email` varchar(200) DEFAULT NULL,
    PRIMARY KEY('id')
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;