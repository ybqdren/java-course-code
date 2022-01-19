/*
 Navicat Premium Data Transfer

 Source Server         : mmSql
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 127.0.0.1:3306
 Source Schema         : test_jpa

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 19/01/2022 11:40:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, 'ybqdren', 'withzhaowen@126.com');
INSERT INTO `user` VALUES (3, 'zhangsan', 'zhangsan@126.com');
INSERT INTO `user` VALUES (4, 'wantwu', 'wantwu@126.com');
INSERT INTO `user` VALUES (5, 'lisi', 'lisi@126.com');
INSERT INTO `user` VALUES (6, 'xiaowu', 'xiaowu@126.com');
INSERT INTO `user` VALUES (7, 'daqi', 'daqi@126.com');
INSERT INTO `user` VALUES (8, 'jiushi', 'jiushi@126.com');

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `userInfo`;
CREATE TABLE `userInfo`  (
                         `id` int(0) NOT NULL AUTO_INCREMENT,
                         `firstName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `lastName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         `emailAddress` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userInfo
-- ----------------------------
INSERT INTO `userInfo` VALUES (2, 'wen', 'zhao','withzhaowen@126.com');