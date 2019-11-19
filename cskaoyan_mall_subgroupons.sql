/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 19/11/2019 19:09:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for subgroupons
-- ----------------------------
DROP TABLE IF EXISTS `subgroupons`;
CREATE TABLE `subgroupons`  (
  `id` int(11) NOT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `groupon_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subgroupons
-- ----------------------------
INSERT INTO `subgroupons` VALUES (1, 1, 1, 1);
INSERT INTO `subgroupons` VALUES (2, 2, 2, 1);
INSERT INTO `subgroupons` VALUES (3, 3, 3, 1);
INSERT INTO `subgroupons` VALUES (4, 4, 4, 1);
INSERT INTO `subgroupons` VALUES (5, 2, 1, 2);
INSERT INTO `subgroupons` VALUES (6, 2, 2, 2);
INSERT INTO `subgroupons` VALUES (7, 3, 2, 3);
INSERT INTO `subgroupons` VALUES (8, 3, 4, 3);

SET FOREIGN_KEY_CHECKS = 1;
