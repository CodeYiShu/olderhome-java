/*
 Navicat Premium Data Transfer

 Source Server         : MyWeb
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 09/11/2021 15:10:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', 1, NULL);
INSERT INTO `admin` VALUES (7, 'test', '1d80fce6bf702b3b277662c9d5011891', 1, 'HRBZeSEL');
INSERT INTO `admin` VALUES (8, 'testA', 'dfa0453112510b2498130718c845a612', 1, '#ID%^iur');

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bed
-- ----------------------------

-- ----------------------------
-- Table structure for guarder
-- ----------------------------
DROP TABLE IF EXISTS `guarder`;
CREATE TABLE `guarder`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  `olderId` int NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  INDEX `olderId`(`olderId`) USING BTREE,
  CONSTRAINT `guarder_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `guarder_ibfk_2` FOREIGN KEY (`olderId`) REFERENCES `older` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guarder
-- ----------------------------
INSERT INTO `guarder` VALUES (1, 'zhangsan', '123456', 2, 1, NULL);
INSERT INTO `guarder` VALUES (2, 'test', 'c1071e7f0dc12f8afa91fd5955180c89', 2, NULL, 'Sk^PKU6a');
INSERT INTO `guarder` VALUES (4, 'lisi', 'f710eb42270a01fc4899883c4b046a51', 2, NULL, 'fR)@MAOV');
INSERT INTO `guarder` VALUES (5, 'zhaoliu', '66a5756456cf9647cfe73fea399209f7', 2, NULL, '3%6CmlDL');
INSERT INTO `guarder` VALUES (6, 'aaa', '8a99923e1dee60c24883333e7b8844be', 2, NULL, 'y8JVwb0y');

-- ----------------------------
-- Table structure for older
-- ----------------------------
DROP TABLE IF EXISTS `older`;
CREATE TABLE `older`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of older
-- ----------------------------
INSERT INTO `older` VALUES (1, 'zhanglao', '100');
INSERT INTO `older` VALUES (2, 'lilao', '100');
INSERT INTO `older` VALUES (3, 'wanglao', '100');
INSERT INTO `older` VALUES (4, 'huanglao', '100');
INSERT INTO `older` VALUES (5, 'linlao', '100');
INSERT INTO `older` VALUES (6, 'chenlao', '100');
INSERT INTO `older` VALUES (7, 'yanglao', '100');
INSERT INTO `older` VALUES (8, 'yonglao', '100');
INSERT INTO `older` VALUES (9, 'hulao', '100');
INSERT INTO `older` VALUES (10, 'sunlao', '100');
INSERT INTO `older` VALUES (11, 'zhaolao', '100');
INSERT INTO `older` VALUES (12, 'zhoulao', '100');
INSERT INTO `older` VALUES (13, 'wulao', '100');
INSERT INTO `older` VALUES (14, 'liulao', '100');
INSERT INTO `older` VALUES (15, 'hulao', '100');
INSERT INTO `older` VALUES (16, 'helao', '100');
INSERT INTO `older` VALUES (17, 'songlao', '100');
INSERT INTO `older` VALUES (18, 'malao', '100');
INSERT INTO `older` VALUES (19, 'luolao', '100');
INSERT INTO `older` VALUES (20, 'dinglao', '100');
INSERT INTO `older` VALUES (21, 'ronglao', '100');
INSERT INTO `older` VALUES (22, 'xianlao', '100');
INSERT INTO `older` VALUES (23, 'fanlao', '100');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'guarder');
INSERT INTO `role` VALUES (3, 'staff');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL,
  `roleId` int NULL DEFAULT NULL,
  `permissionId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'zhaoliu', '123456', 3, NULL);
INSERT INTO `staff` VALUES (2, 'test', 'bb1887de4a6abc9b1c56886505003942', 3, 'KZi)KbY$');
INSERT INTO `staff` VALUES (3, 'wangwu', '578364330be0aace1a31664fc3b4b2e2', 3, '4^UL&8J8');

SET FOREIGN_KEY_CHECKS = 1;
