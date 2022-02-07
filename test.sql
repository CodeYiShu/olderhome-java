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

 Date: 07/02/2022 15:14:28
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
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (8, 'testA', 'dfa0453112510b2498130718c845a612', '测试A', 1, '#ID%^iur');
INSERT INTO `admin` VALUES (14, 'test', '0cac7c4bbf7eb7f56b9fe859825edc6a', '测试2', 1, '&$7B6jGE');
INSERT INTO `admin` VALUES (15, 'zhangsan', 'a00348a128fd71171763d0d8d60e4293', '张三', 1, 'Nuc#jRg8');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `adminId` int NULL DEFAULT NULL COMMENT '管理员id',
  `time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '标题啊', '内容啊', 14, '2022-02-04 22:31:01');
INSERT INTO `article` VALUES (2, '标', '内容', 14, '2022-02-05 15:30:04');
INSERT INTO `article` VALUES (44, '33', '<p>33<br/></p>', 14, '2022-02-06 15:40:59');
INSERT INTO `article` VALUES (45, '牛皮', '<h1 data-we-empty-p=\"\" id=\"e4n8w\">牛皮咯<br/></h1>', 14, '2022-02-06 15:57:53');
INSERT INTO `article` VALUES (46, 'wangEditor', '<p>【通知】<b>wangEditor 新版本 V5</b> 已公开测试，可查阅 <a href=\"https://www.wangeditor.com/v5/\">V5 文档</a>。</p><h1>为何选择 wangEditor</h1><ul><li>简洁、轻量级、<a href=\"https://www.wangeditor.com/doc/\">文档</a>齐全</li><li>万星项目 <a href=\"https://github.com/wangeditor-team/wangEditor/releases\">Github Star 1w+</a></li><li>npm 周下载量 1w+</li><li>CDN 月下载量百万+（来自 <a href=\"https://www.jsdelivr.com/package/npm/wangeditor\">jsdelivr</a>）</li><li>QQ 群及时答疑</li><li><a href=\"https://www.wangeditor.com/doc/#开发人员\">开源团队</a>维护，非个人单兵作战</li></ul><h1>初见</h1><p>npm 安装 <code>npm i wangeditor --save</code> ，几行代码即可创建一个编辑器。</p><pre><code>import E from \'wangeditor\'\nconst editor = new E(\'#div1\')\neditor.create()</code></pre><p>更多使用配置，请阅读<a href=\"https://www.wangeditor.com/doc/\">使用文档</a>。</p><h1>demo</h1><p>在线体验 demo 可到 <a href=\"https://codepen.io/collection/DNmPQV\">codepen.io/collection/DNmPQV</a> 。</p><p>注意，如果打不开，可以去查看 <a href=\"https://github.com/wangeditor-team/wangEditor/tree/master/examples\">github examples</a> 的源码。</p><h1>浏览器兼容性</h1><ul><li>兼容主流 PC 浏览器，IE11+</li><li>不支持移动端和 ipad</li></ul><h1>遇到问题</h1><ul><li>加入 QQ 群：164999061(人已满)，710646022(人已满)，901247714(人已满)，606602511(人已满)，764778054</li><li>\n                                <a href=\"https://github.com/wangeditor-team/wangEditor/issues\" target=\"_blank\">\n                                    提交问题和建议\n                                </a>\n                            </li></ul><h1>贡献代码</h1><p>欢迎非团队成员贡献代码，提交 Pull Request，请一定参考<a href=\"https://github.com/wangeditor-team/wangEditor/blob/master/docs/contribution.md\" target=\"_blank\">贡献代码流程</a>。</p><h1>谁在维护</h1><p>wangEditor 现有一个开源团队在维护，团队可以保证答疑、bug 修复和迭代效率。</p><p><a href=\"https://www.wangeditor.com/doc/#开发人员\">查看开发团队，或想加入开发团队</a></p><h1>为我们点赞</h1><p>如果你感觉有收获，欢迎给我打赏，以激励我们更多输出优质开源内容。</p>', 14, '2022-02-06 15:59:04');
INSERT INTO `article` VALUES (47, 'zzzz', '<p>zzz<br/></p>', 14, '2022-02-06 16:05:48');

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
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `bed` VALUES (18, '100号床', NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cost
-- ----------------------------
INSERT INTO `cost` VALUES (1, 1, 2000, '未缴');
INSERT INTO `cost` VALUES (2, 2, 400, '已缴');
INSERT INTO `cost` VALUES (3, 3, 5000, '未缴');
INSERT INTO `cost` VALUES (4, 25, 400, '已缴');
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
INSERT INTO `cost` VALUES (46, 41, 100, '已缴');
INSERT INTO `cost` VALUES (47, 42, 5000, '未缴');
INSERT INTO `cost` VALUES (48, 45, 100, '未缴');
INSERT INTO `cost` VALUES (49, 46, 100, '未缴');

-- ----------------------------
-- Table structure for guarder
-- ----------------------------
DROP TABLE IF EXISTS `guarder`;
CREATE TABLE `guarder`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `guarder_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guarder
-- ----------------------------
INSERT INTO `guarder` VALUES (1, 'zhangsan', '123456', '张三阿', 30, '男', '13547895541', 2, '123');
INSERT INTO `guarder` VALUES (4, 'lisi', 'f710eb42270a01fc4899883c4b046a51', '李四', 30, '男', '13458798541', 2, 'fR)@MAOV');
INSERT INTO `guarder` VALUES (5, 'zhaoliu', '66a5756456cf9647cfe73fea399209f7', '赵六', 36, '女', '13245879541', 2, '3%6CmlDL');
INSERT INTO `guarder` VALUES (7, 'wangwu', '3ad3731a2c09b2dea4782b67ca3cc3b0', '王五', 100, '男', '123', 2, '#&fyvLhZ');
INSERT INTO `guarder` VALUES (8, 'dalao', '473e2d124bc403546f0d0c3d4e186540', '大佬', 123, '男', '123', 2, '29du^aC&');
INSERT INTO `guarder` VALUES (10, 'jianhuren', 'b22db339680fba9514e917d2a8839d84', '监护人', 100, '男', '123', 2, '^4Hw4mgb');
INSERT INTO `guarder` VALUES (11, 'test', '4b5dfba939efc6658b7c9e3c186febd6', '测试a ', 25, '女', '123', 2, 'i3Rw5qWw');
INSERT INTO `guarder` VALUES (12, 'test2', '89d54ef6b6a6514cec08b4450591ad20', '测试2', 123, '男', '123', 2, '4C2f#MIa');
INSERT INTO `guarder` VALUES (14, 'liuliu', 'f31f1540fddf57d73640bd97b9008353', '六六', NULL, NULL, NULL, 2, 'DOV#PKiv');
INSERT INTO `guarder` VALUES (15, 'jianhuren2', '19c73f52728fc170a720762d35e205fa', '监护人2', 100, '女', '123', 2, 'UzSyFnbq');
INSERT INTO `guarder` VALUES (16, 'jianhuaa', 'e4fbf94d1052bf2386e05c534a8527ba', '监护nn', 100, '女', '321', 2, 'DMoVfhy8');

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
  `enterDate` datetime(0) NULL DEFAULT NULL COMMENT '入住时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of older
-- ----------------------------
INSERT INTO `older` VALUES (1, 'zhanglao', '103', '13828528428', 1, '健康', NULL);
INSERT INTO `older` VALUES (2, 'lilao', '101', '13828528422', 11, '健康', NULL);
INSERT INTO `older` VALUES (3, 'wanglao', '100', '13828528421', 2, '健康', NULL);
INSERT INTO `older` VALUES (4, 'huanglao', '100', '13828528423', 2, '健康', NULL);
INSERT INTO `older` VALUES (5, 'linlao', '100', '13828528424', 4, '健康', NULL);
INSERT INTO `older` VALUES (6, 'chenlao', '100', '13828528425', 4, '健康', NULL);
INSERT INTO `older` VALUES (7, 'yanglao', '100', '13828528426', 1, '健康', NULL);
INSERT INTO `older` VALUES (8, 'yonglao', '100', '13828528427', 5, '健康', NULL);
INSERT INTO `older` VALUES (9, 'hulao', '100', '13828528429', 7, '健康', '2021-10-01 01:58:16');
INSERT INTO `older` VALUES (10, 'sunlao', '100', '13828528418', 3, '健康', '2021-11-01 01:58:12');
INSERT INTO `older` VALUES (11, 'zhaolao', '100', '13828528438', 2, '健康', '2021-04-01 01:58:10');
INSERT INTO `older` VALUES (12, 'zhoulao', '100', '13828528448', 1, '健康', '2021-12-01 01:58:06');
INSERT INTO `older` VALUES (13, 'wulao', '100', '13828528458', 3, '健康', '2022-01-29 01:58:03');
INSERT INTO `older` VALUES (14, 'liulao', '100', '13828528468', 4, '健康', '2022-02-28 01:57:58');
INSERT INTO `older` VALUES (15, 'hulao', '100', '13828528478', 5, '健康', '2021-02-01 01:57:48');
INSERT INTO `older` VALUES (16, 'helao', '100', '13828528488', 6, '健康', '2022-02-28 01:57:44');
INSERT INTO `older` VALUES (17, 'songlao', '100', '13828528498', 7, '健康', '2021-03-29 01:57:38');
INSERT INTO `older` VALUES (18, 'malao', '100', '13828528408', 8, '健康', '2021-05-29 01:57:21');
INSERT INTO `older` VALUES (19, 'luolao', '100', '13828528128', 7, '健康', '2021-05-29 01:57:15');
INSERT INTO `older` VALUES (20, 'dinglao', '100', '13828528228', 9, '健康', '2021-06-29 01:57:08');
INSERT INTO `older` VALUES (21, 'ronglao', '100', '13828528328', 6, '健康', '2021-07-01 01:57:01');
INSERT INTO `older` VALUES (22, 'xianlao', '100', '13828528428', 4, '健康', '2022-02-01 01:56:58');
INSERT INTO `older` VALUES (23, 'fanlao', '100', '13828528528', 9, '健康', '2022-02-01 01:56:52');
INSERT INTO `older` VALUES (25, '111', '100', '13828528628', 11, '观察', '2021-08-05 01:56:38');
INSERT INTO `older` VALUES (29, 'laoren', '100', NULL, 7, '紧急', '2021-08-01 01:56:32');
INSERT INTO `older` VALUES (30, 'test', '100', '123456789', 11, '紧急', '2021-09-01 01:56:26');
INSERT INTO `older` VALUES (31, 'test2', '100', '123', 11, '紧急', '2022-02-01 01:56:21');
INSERT INTO `older` VALUES (33, '13', '100', '13', 12, NULL, '2021-10-01 01:56:14');
INSERT INTO `older` VALUES (34, '14', '100', '123', 12, NULL, '2021-10-01 01:56:09');
INSERT INTO `older` VALUES (35, '30', '100', '123', 12, NULL, '2021-10-01 01:56:03');
INSERT INTO `older` VALUES (36, '31', '100', '123', 12, NULL, '2021-11-01 01:55:57');
INSERT INTO `older` VALUES (37, '133', '100', '123', 11, NULL, '2021-12-01 01:55:52');
INSERT INTO `older` VALUES (38, '122', '100', '123', 11, NULL, '2021-12-01 01:55:44');
INSERT INTO `older` VALUES (41, '12312', '130', '12211', 11, NULL, '2022-02-02 01:55:36');
INSERT INTO `older` VALUES (43, 'zhangsan', NULL, NULL, 1, NULL, '2022-02-01 00:00:00');
INSERT INTO `older` VALUES (44, 'zhangsan', NULL, NULL, 1, NULL, '2021-01-29 00:00:00');
INSERT INTO `older` VALUES (45, '', '100', '333', 1, NULL, '2022-01-29 00:00:00');
INSERT INTO `older` VALUES (46, '123123', '100', '4654', 1, NULL, '2022-01-29 00:00:00');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `costId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 'aaa11', 10);
INSERT INTO `orders` VALUES (7, '1bd9a8940d254d7badbd1d2ee190c401', 45);
INSERT INTO `orders` VALUES (10, '18b953269de5454a8e63124ab6db5f3a', 46);
INSERT INTO `orders` VALUES (11, 'e2663c51c2564af8a42837512d5e5484', 46);
INSERT INTO `orders` VALUES (12, '0249c8a391db4d66a7153cfb3eb1d70b', 46);
INSERT INTO `orders` VALUES (13, '615c31e9f3164a118c26333d5188dc2b', 46);
INSERT INTO `orders` VALUES (18, 'e7a301ad02a549858bd44ab83deb94a2', 4);
INSERT INTO `orders` VALUES (19, 'ec1d33fbc5af44239550775ff423acac', 2);
INSERT INTO `orders` VALUES (20, '952b7a97fc314f90b704d9ee77c0d394', 4);
INSERT INTO `orders` VALUES (21, 'a53c1bcc8041467691b84d7d451eaa41', 4);
INSERT INTO `orders` VALUES (22, '881f1a52a14a461fa5eee896116a535b', 4);
INSERT INTO `orders` VALUES (23, 'e7401d3d9b4b4c33b0dee4f6f5abf69f', 4);
INSERT INTO `orders` VALUES (24, '235f5eb2ff9348ad88b91ff3fa309748', 1);
INSERT INTO `orders` VALUES (25, '0bd3e699d4bd431bb06857a656bf9955', 4);

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
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int NULL DEFAULT NULL,
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '男:1，女:0',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (2, 'test', 'fec2798e18b7e42a07eb2b1e038fe263', '测试B ', 3, 'G*3is(U4', 23, '男', '123');
INSERT INTO `staff` VALUES (4, 'yihu1', '72d9ead984cf67b40fdf07e3ac2bc9d0', '医护1', 3, 'NFV8#DUt', 22, '男', '13247795620');
INSERT INTO `staff` VALUES (5, 'yihu2', '2ef51ff6c6c2f3a5f7aa1a4663705f7f', '医护2', 3, 'HG(*jWPk', 22, '女', '13547895640');
INSERT INTO `staff` VALUES (6, 'yihu3', '62cb35b012bcababf0451cdaea80bdc0', '医护3', 3, '$oGSmTWy', 33, '男', '1033611821');
INSERT INTO `staff` VALUES (7, 'yihu4', 'eb53999db5962c7396b35f5242c8ab29', '医护4', 3, '^*7j5E)2', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (8, 'yihu5', '352ed6e8e1f16c307f5c27d19331a3a2', '医护5', 3, 'g6SvW*c(', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (9, 'yihu6', 'c56f28adbe219cbe3908167ad401b9d9', '医护6', 3, '&2Jklrpq', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (11, 'yihu7', '2c1ad6e887b76a3370d00f5bd7d46b69', '医护7', 3, 'gEfHSCQ6', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (12, 'yihu8', '75368e3d81e9abedd3cce384d9e9da44', '医护8', 3, 'l(fQ%&I(', 100, '男', '123');
INSERT INTO `staff` VALUES (13, 'yihu9', '426ef218d1dc329f0395f463cccd193e', '医护9', 3, 'MuYKhCh2', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (15, 'yihu10', 'cc29223b26690da01dbda1ac1ad0e01e', '医护10', 3, '#gT9HV6N', 100, '女', '123');
INSERT INTO `staff` VALUES (17, 'jianhu13', 'a035a75d2a83fb53029a563a0f9163a0', NULL, 3, 'np!GeARV', 100, '女', '321');
INSERT INTO `staff` VALUES (18, 'jianhu14', 'eafd1fc7e29327cdce4108cb1ea29a9c', NULL, 3, 'JK6SBCbP', 100, '女', '123');
INSERT INTO `staff` VALUES (19, 'yihu11', '5b1bb1894fa2aaf9e74975f05bff7937', '医护11', 3, '(zLaEVau', 100, '女', '321');
INSERT INTO `staff` VALUES (20, 'yihu12', '8019a9f9191eefe6d45fc1f36cdc0017', '医护12', 3, 'L@Ap8VrQ', 100, '女', '123');
INSERT INTO `staff` VALUES (21, 'yihuaa', '13575800aa1b09d2de42739bffe88cc9', '医护cc', 3, '5BS#SbmD', 100, '女', '321');

SET FOREIGN_KEY_CHECKS = 1;
