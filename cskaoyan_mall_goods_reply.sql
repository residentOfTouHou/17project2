/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : sb_project

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 19/11/2019 16:46:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cskaoyan_mall_goods_reply
-- ----------------------------
DROP TABLE IF EXISTS `cskaoyan_mall_goods_reply`;
CREATE TABLE `cskaoyan_mall_goods_reply`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `content` varchar(1023) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cskaoyan_mall_goods_reply
-- ----------------------------
INSERT INTO `cskaoyan_mall_goods_reply` VALUES (1, 1011, '666', '2019-11-18 23:01:25', NULL);
INSERT INTO `cskaoyan_mall_goods_reply` VALUES (2, 512, '111', '2019-11-18 23:01:40', NULL);

SET FOREIGN_KEY_CHECKS = 1;
