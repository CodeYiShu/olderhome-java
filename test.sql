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

 Date: 16/12/2021 22:02:25
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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (8, 'testA', 'dfa0453112510b2498130718c845a612', 1, '#ID%^iur');
INSERT INTO `admin` VALUES (14, 'test', 'ca807779f6ecac44752bb343f62ef595', 1, 'i%ip#g^$');

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `olderId` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL COMMENT '是否空闲，0表示空闲',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES (1, '1号床', 5, 1);
INSERT INTO `bed` VALUES (2, '2号床', 3, 1);
INSERT INTO `bed` VALUES (4, '4号床', 2, 1);
INSERT INTO `bed` VALUES (5, '11号床', 5, 1);
INSERT INTO `bed` VALUES (6, '21号床', 1, 1);
INSERT INTO `bed` VALUES (8, '31号床位', 1, 1);
INSERT INTO `bed` VALUES (9, '41号床位', NULL, 1);
INSERT INTO `bed` VALUES (10, '51号床位', NULL, 0);
INSERT INTO `bed` VALUES (11, '20号床', NULL, 0);
INSERT INTO `bed` VALUES (12, '22号床', 5, 1);
INSERT INTO `bed` VALUES (15, '32号床', 4, 1);

-- ----------------------------
-- Table structure for cost
-- ----------------------------
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `olderId` int NULL DEFAULT NULL COMMENT '老人的id',
  `total` int NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cost
-- ----------------------------
INSERT INTO `cost` VALUES (1, 1, 2000, '已缴');
INSERT INTO `cost` VALUES (2, 2, 400, '已缴');
INSERT INTO `cost` VALUES (3, 3, 5000, '未缴');
INSERT INTO `cost` VALUES (4, 25, 5000, '已缴');
INSERT INTO `cost` VALUES (5, 30, 5000, '已缴');
INSERT INTO `cost` VALUES (6, 31, 5000, '已缴');
INSERT INTO `cost` VALUES (9, 36, 5000, '已缴');
INSERT INTO `cost` VALUES (10, 37, 5000, '已缴');
INSERT INTO `cost` VALUES (12, 4, 200, '未缴');
INSERT INTO `cost` VALUES (13, 5, 200, '未缴');
INSERT INTO `cost` VALUES (14, 6, 200, '未缴');
INSERT INTO `cost` VALUES (15, 7, 400, '未缴');
INSERT INTO `cost` VALUES (16, 8, 300, '未缴');
INSERT INTO `cost` VALUES (17, 9, 300, '未缴');
INSERT INTO `cost` VALUES (18, 10, 200, '未缴');
INSERT INTO `cost` VALUES (19, 11, 100, '未缴');
INSERT INTO `cost` VALUES (20, 12, 100, '未缴');
INSERT INTO `cost` VALUES (21, 13, 100, '未缴');
INSERT INTO `cost` VALUES (22, 14, 100, '未缴');
INSERT INTO `cost` VALUES (23, 15, 100, '未缴');
INSERT INTO `cost` VALUES (24, 16, 100, '已缴');
INSERT INTO `cost` VALUES (25, 17, 300, '未缴');
INSERT INTO `cost` VALUES (26, 18, 200, '未缴');
INSERT INTO `cost` VALUES (27, 20, 300, '未缴');
INSERT INTO `cost` VALUES (28, 21, 10, '未缴');
INSERT INTO `cost` VALUES (29, 22, 10, '未缴');
INSERT INTO `cost` VALUES (30, 19, 10, '未缴');
INSERT INTO `cost` VALUES (31, 23, 10, '未缴');
INSERT INTO `cost` VALUES (32, 24, 10, '未缴');
INSERT INTO `cost` VALUES (33, 26, 10, '未缴');
INSERT INTO `cost` VALUES (34, 27, 10, '未缴');
INSERT INTO `cost` VALUES (35, 28, 10, '未缴');
INSERT INTO `cost` VALUES (36, 29, 10, '未缴');
INSERT INTO `cost` VALUES (39, 32, 10, '未缴');
INSERT INTO `cost` VALUES (40, 33, 10, '未缴');
INSERT INTO `cost` VALUES (41, 34, 10, '未缴');
INSERT INTO `cost` VALUES (42, 35, 10, '未缴');
INSERT INTO `cost` VALUES (45, 38, 10, '已缴');
INSERT INTO `cost` VALUES (46, 41, 100, '未缴');
INSERT INTO `cost` VALUES (47, 42, 5000, '已缴');

-- ----------------------------
-- Table structure for guarder
-- ----------------------------
DROP TABLE IF EXISTS `guarder`;
CREATE TABLE `guarder`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `guarder_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guarder
-- ----------------------------
INSERT INTO `guarder` VALUES (1, 'zhangsan', '123456', 30, '男', '13547895541', 2, '123');
INSERT INTO `guarder` VALUES (4, 'lisi', 'f710eb42270a01fc4899883c4b046a51', 30, '男', '13458798541', 2, 'fR)@MAOV');
INSERT INTO `guarder` VALUES (5, 'zhaoliu', '66a5756456cf9647cfe73fea399209f7', 36, '女', '13245879541', 2, '3%6CmlDL');
INSERT INTO `guarder` VALUES (7, 'wangwu', '3ad3731a2c09b2dea4782b67ca3cc3b0', 100, '男', '123', 2, '#&fyvLhZ');
INSERT INTO `guarder` VALUES (8, 'dalao', '473e2d124bc403546f0d0c3d4e186540', 123, '男', '123', 2, '29du^aC&');
INSERT INTO `guarder` VALUES (10, 'jianhuren', 'b22db339680fba9514e917d2a8839d84', 100, '男', '123', 2, '^4Hw4mgb');
INSERT INTO `guarder` VALUES (11, 'test', 'ce604ca9db8447a9e6324f85b15274c3', 22, '男', '123', 2, '#k@pv&%Q');
INSERT INTO `guarder` VALUES (12, 'test2', '89d54ef6b6a6514cec08b4450591ad20', 123, '男', '123', 2, '4C2f#MIa');

-- ----------------------------
-- Table structure for older
-- ----------------------------
DROP TABLE IF EXISTS `older`;
CREATE TABLE `older`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `guarderId` int NULL DEFAULT NULL,
  `health` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康情况：健康、观察和紧急',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of older
-- ----------------------------
INSERT INTO `older` VALUES (1, 'zhanglao', '103', '13828528428', 1, '健康');
INSERT INTO `older` VALUES (2, 'lilao', '101', '13828528422', 11, '健康');
INSERT INTO `older` VALUES (3, 'wanglao', '100', '13828528421', 2, '健康');
INSERT INTO `older` VALUES (4, 'huanglao', '100', '13828528423', 2, '健康');
INSERT INTO `older` VALUES (5, 'linlao', '100', '13828528424', 4, '健康');
INSERT INTO `older` VALUES (6, 'chenlao', '100', '13828528425', 4, '健康');
INSERT INTO `older` VALUES (7, 'yanglao', '100', '13828528426', 1, '健康');
INSERT INTO `older` VALUES (8, 'yonglao', '100', '13828528427', 5, '健康');
INSERT INTO `older` VALUES (9, 'hulao', '100', '13828528429', 7, '健康');
INSERT INTO `older` VALUES (10, 'sunlao', '100', '13828528418', 3, '健康');
INSERT INTO `older` VALUES (11, 'zhaolao', '100', '13828528438', 2, '健康');
INSERT INTO `older` VALUES (12, 'zhoulao', '100', '13828528448', 1, '健康');
INSERT INTO `older` VALUES (13, 'wulao', '100', '13828528458', 3, '健康');
INSERT INTO `older` VALUES (14, 'liulao', '100', '13828528468', 4, '健康');
INSERT INTO `older` VALUES (15, 'hulao', '100', '13828528478', 5, '健康');
INSERT INTO `older` VALUES (16, 'helao', '100', '13828528488', 6, '健康');
INSERT INTO `older` VALUES (17, 'songlao', '100', '13828528498', 7, '健康');
INSERT INTO `older` VALUES (18, 'malao', '100', '13828528408', 8, '健康');
INSERT INTO `older` VALUES (19, 'luolao', '100', '13828528128', 7, '健康');
INSERT INTO `older` VALUES (20, 'dinglao', '100', '13828528228', 9, '健康');
INSERT INTO `older` VALUES (21, 'ronglao', '100', '13828528328', 6, '健康');
INSERT INTO `older` VALUES (22, 'xianlao', '100', '13828528428', 4, '健康');
INSERT INTO `older` VALUES (23, 'fanlao', '100', '13828528528', 9, '健康');
INSERT INTO `older` VALUES (25, '111', '100', '13828528628', 11, '观察');
INSERT INTO `older` VALUES (29, 'laoren', '100', NULL, 7, '紧急');
INSERT INTO `older` VALUES (30, 'test', '100', '123456789', 11, '紧急');
INSERT INTO `older` VALUES (31, 'test2', '100', '123', 11, '紧急');
INSERT INTO `older` VALUES (33, '13', '100', '13', 12, NULL);
INSERT INTO `older` VALUES (34, '14', '100', '123', 12, NULL);
INSERT INTO `older` VALUES (35, '30', '100', '123', 12, NULL);
INSERT INTO `older` VALUES (36, '31', '100', '123', 12, NULL);
INSERT INTO `older` VALUES (37, '133', '100', '123', 11, NULL);
INSERT INTO `older` VALUES (38, '122', '100', '123', 11, NULL);
INSERT INTO `older` VALUES (41, '12312', '130', '12211', 11, NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `costId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 'aaa11', 10);
INSERT INTO `orders` VALUES (7, '1bd9a8940d254d7badbd1d2ee190c401', 45);
INSERT INTO `orders` VALUES (10, '18b953269de5454a8e63124ab6db5f3a', 46);
INSERT INTO `orders` VALUES (11, 'e2663c51c2564af8a42837512d5e5484', 46);

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
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NULL DEFAULT NULL,
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '男:1，女:0',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (2, 'test', '621638351bf643118eaa347a74efd7d8', 3, 'Wkq1K!q6', 22, '女', '13548752102');
INSERT INTO `staff` VALUES (4, 'yihu1', '72d9ead984cf67b40fdf07e3ac2bc9d0', 3, 'NFV8#DUt', 22, '男', '13247795620');
INSERT INTO `staff` VALUES (5, 'yihu2', '2ef51ff6c6c2f3a5f7aa1a4663705f7f', 3, 'HG(*jWPk', 22, '女', '13547895640');
INSERT INTO `staff` VALUES (6, 'yihu3', '62cb35b012bcababf0451cdaea80bdc0', 3, '$oGSmTWy', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (7, 'yihu4', 'eb53999db5962c7396b35f5242c8ab29', 3, '^*7j5E)2', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (8, 'yihu5', '352ed6e8e1f16c307f5c27d19331a3a2', 3, 'g6SvW*c(', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (9, 'yihu6', 'c56f28adbe219cbe3908167ad401b9d9', 3, '&2Jklrpq', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (11, 'yihu7', '2c1ad6e887b76a3370d00f5bd7d46b69', 3, 'gEfHSCQ6', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (12, 'yihu8', '75368e3d81e9abedd3cce384d9e9da44', 3, 'l(fQ%&I(', 100, '男', '123');
INSERT INTO `staff` VALUES (13, 'yihu9', '426ef218d1dc329f0395f463cccd193e', 3, 'MuYKhCh2', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (15, 'yihu10', 'cc29223b26690da01dbda1ac1ad0e01e', 3, '#gT9HV6N', 100, '女', '123');

SET FOREIGN_KEY_CHECKS = 1;
