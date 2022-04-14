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

 Date: 14/04/2022 21:50:58
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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (8, 'zhangsan', 'dfa0453112510b2498130718c845a612', '张一术', 1, '#ID%^iur');
INSERT INTO `admin` VALUES (14, 'test', '0cac7c4bbf7eb7f56b9fe859825edc6a', '张二术', 1, '&$7B6jGE');
INSERT INTO `admin` VALUES (15, 'admin', 'a00348a128fd71171763d0d8d60e4293', '张三术', 1, 'Nuc#jRg8');

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
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (51, '「健康报」养老院里“病有所医”', '<p>“以前，去乡卫生院看病，走路要半个小时。如今，养老中心有了医生，不出院门就能看病了。”近日，在江西省赣州市于都县登贤养老服务中心医疗点，“五保”老人张其英患感冒，治疗两天后，病情有了好转。</p><p>了解到老人左眼看东西模糊，值班医生检查后，发现老人患的是白内障。“现在做这项手术是免费的。我们会安排好去医院做手术。”医生检查后说道。贴心的医疗服务，让82岁的张其英笑得合不拢嘴。</p><p>今年2月，小溪乡卫生院在登贤养老服务中心开设医养结合医疗点，添置复苏气囊、制氧机、雾化器等医疗设备，配备治疗室、输液观察室、药房及病床，每天选派医生和护士全天候值班。从此，68名在院老人足不出“院”就能获得常见病、多发病诊疗和健康体检等服务。</p><p>记者在登贤养老服务中心医疗点看到，这边护士忙着给输液的老人更换药水，调节输液速率；那边，值班医生肖俊廷和中心护理员为行动不便、患有高血压的黄有娣测量血压。“按我教您的用法用量吃这两种药，我过几天再来给您量血压，看看治疗效果。”当发现黄有娣的血压控制不理想后，肖俊廷耐心指导用药，反复嘱咐老人按时服药。</p><p>据养老服务中心负责人肖军介绍，中心运行后，医疗点紧接着就开设起来，实现了住院老人养老、医疗无缝衔接，确保了在院老人老有所养、老有所医。</p><p>今年，于都县把推动公办养老机构改革，构建居家社区机构相协调、医养康养相结合的养老服务体系列入改革任务清单，实施销号管理。通过在养老机构设置内嵌式卫生院分院（医疗服务点），在全县二级以上医院设置老年医学科，就近规划建设医疗和养老机构，鼓励民间力量兴建社区医养结合型医院，推动产权公有村卫生室建设全覆盖等途径，实现养老机构和医疗机构无缝对接。</p><p>截至2月底，葛坳曲洋光荣敬老院等11家新建养老机构同步建成院内嵌入式医疗点，老年人常见病、多发病基本上在院内或本乡镇范围内就可以治疗。</p><p>【来源：江西省卫健委_媒体报道】</p><p>声明：此文版权归原作者所有，若有来源错误或者侵犯您的合法权益，您可通过邮箱与我们取得联系，我们将及时进行处理。邮箱地址：jpbl@jp.jiupainews.com</p><p></p>', 15, '2022-04-14 21:31:21');
INSERT INTO `article` VALUES (52, '分享卢微老师美文：走近养老院——感受生命迟暮之美', '<span>5月27日，第一次带学生走进老人院，也是我第一次如此亲近地走进在这里生活的老人们。从前都只是听说，到了七老八十，儿女们都忙于工作，或是离得较远，大都是心有余而力不足，无暇照顾，老人们只能住进养老院。也从网络报纸上，看到关于养老院的负面报道，比如虐待老人等等，让人心酸又无奈。不过，昨天我们去的福州老人公寓，彻底改变了我对老人院的看法，这里环境卫生都极好，大门口挂满了各种奖状，且各种娱乐体育设施也不少，还有他们的生活也是很丰富的，会组织各种文艺活动等。</span><p><span>\n    \n昨天，和学生们在这里逗留了1个半小时，大家陪着老人们聊天，唱歌，表演，老人们也很开心，和同学们说了好一会儿话，在我看来，每一位老人这辈子经历过的事都可以写成一本厚厚的书了，他们对于人生的感悟和宽阔的胸怀，都是我们应该虚心学习的。这里是一方净土，可以净化尘世中愈发浮躁的心灵，也是一个个人生的缩影，让我们去领悟活着的意义。</span></p><p>    其中，有几位老人是经历过丧子之痛的，可是再聊起时，他们已是很淡然了，仿佛恍若隔世，放下了，便也没有那么多的伤心了。</p><p><span>\n    \n还有一位95岁的老爷爷，毕业于著名的黄埔军校，当了大半辈子的兵，，曾经军功煊赫，是响当当的团长，虽已快100岁，但腰板依然挺直，声音还是那么洪亮，唱起京剧还是那么字正腔圆，他的三个孩子都在美国生活，他因为不习惯，就回到了老家，住在了这里，他一个月的工资要8000呢。\n    \n老人们毕生的经历都刻画在了沧桑的面庞和深深的皱纹里，虽住了这里，他们说还是想家的，我想他们也会经常回忆年轻时的岁月吧，只是缺少倾听的人，想想我们的父母及爷爷奶奶、姥姥姥爷，一年中又有多少时间可以真正的陪着他们，静静地听他们说话呢？生命无常，生命易逝，好好珍惜吧。有一个令人心酸的算术题，有人做了这样的统计：\n    \n假如你老家在外地，一年中只有春节回家看看，回家只有七天，除去应酬，聚会，吃饭，睡觉，真正能够陪父母的时间可能每天只有3个小时，一个星期只有21个小时。中国人的平均寿命是72岁。假如父母能活到85岁，假设父母现在是55岁。那她们在这个世界只剩下30年。30年×21小时，你能在她们身边的时间是630个小时，也就是26天。。。</span><br/></p><p>怎么去拥有一道彩虹    怎么去拥抱一夏天的风    天上的星星笑地上的人    总是不能懂不能觉得足够</p><p>    如果我爱上你的笑容    要怎么收藏要怎么拥有    如果你快乐不是为我    会不会放手其实才是拥有    当一阵风吹来风筝飞上天空    为了你而祈祷而祝福而感动    终于你身影消失在人海尽头    才发现笑着哭最痛。。。</p><p>    后记：下面是学生们在周记里写下的有关这次活动的真实感受：</p><p>    林星：觉得时间过<span>的</span>太快了，真希望老师再早点安排去一次老人院。</p><p>    钟素莹：很快到了4点半了，我们的活动该结束了，看到很多老人都很不舍，我们说：“爷爷奶奶，下次我们还会来的。”看到孤独的老人，希望下次来的快些！</p><p>    欧梅莲：他们唯一<span>有</span>一点就是很想家，但他们不想成为子女的负担，宁愿搬进老人院，也不想拖累子女。。。</p><p>    翁艳芬：我的爷爷奶奶，虽然有时周末会去他们家里，但是待的时间也不长，我想以后要多去陪陪他们！</p><p>    钱洁：他们家中虽富裕，但钱多有什么用？没有子女在身边，怎么开心的起来？换<span>做</span>是我，我宁愿自己少挣钱，也要照顾好父母！</p><p></p>', 15, '2022-04-14 21:35:01');
INSERT INTO `article` VALUES (53, '一起来品读赞美老年的诗句', '<p><strong>　　（第一首）</strong></p><p>　　养儿防老剩虚名，不孝有三无晚晴。</p><p>　　零落空巢缺对语，蹒跚伉俪断支撑。</p><p>　　忽闻松鹤东山聚，又见夕阳紫气升。</p><p>　　戏彩娱亲民政演，人间温暖可融冰。</p><p>　　------</p><p><strong>　　（第二首）</strong></p><p>　　气爽景幽浮瑞香，老人心里喜洋洋。</p><p>　　情浓仙馆三冬暖，意送清风四夏凉。</p><p>　　漫步广场迎日月，聊天小院话沧桑。</p><p>　　若非时下逢平世，安得余年享乐康。</p><p>　　------</p><p><strong>　　（第三首）</strong></p><p>　　无悔耕耘数十年，不沾水酒不抽烟。</p><p>　　家和万日身趋弱，儿别几冬眼欲穿。</p><p>　　伸颈颤攀书桌上，披衣滞踞灶炉边。</p><p>　　老人公寓居坪屋，守我窗前一片天。</p><p>　　------</p><p><strong>　　（第四首）</strong></p><p>　　今生入住度春秋，世外难捱老迈愁。</p><p>　　卧褥知亲离此远，嚼食叹齿已经丢。</p><p>　　楼前落雪心灰冷，寓内悬蚊苦恼纠。</p><p>　　病患时常拂皓首，黄泉总把耄耋收。</p><p>　　------</p><p><strong>　　（第五首）</strong></p><p>　　时序循环丁酉年，悠然回首似云烟。</p><p>　　栽花结果身添力，盼树成材眼欲穿。</p><p>　　研墨书诗图案上，携妻散步柳湖边。</p><p>　　入居公寓妇随唱，以沫相濡乐满天。</p><p>　　------</p><p><strong>　　（第六首）</strong></p><p>　　泼墨挥毫韵味芳，温泉公寓誉名扬。</p><p>　　情浓意厚贴心袄, 烹饪得宜饭菜香。</p><p>　　品质清新长寿乐，栖居笑语若华堂。</p><p>　　琴棋书画逐花影，无限生机在北汤。</p><p></p>', 15, '2022-04-14 21:36:27');
INSERT INTO `article` VALUES (54, '《秋天的院子——写给敬老院的老人》', '<p><span>作者：黄晓</span></p><p><span>朗诵：</span></p><p><span>院子非常安静</span></p><p><span>老人们菩萨一般坐在秋日的阳光里</span></p><p><span>只有那只大花狗在走来走去仿佛在搜寻</span></p><p><span>空气里那久违的味道</span></p><p><span>阳光从屋檐斜进来</span></p><p><span>整个房间就变成了新的新的床上，铺着花格的新毯子新的桌子上，摆放着新的杯子对面的新墙上</span></p><p><span>一面新的镜子里坐着两个穿着新衣服的老人露着新装的牙齿一直在笑……</span></p><p><span>在崭新的院门边上顺着老人手指的方向我看到那块让他自豪的坡地地里的高粱，如火炬般在阳光闪耀那时，我才突然明白他的名字里为什么紧紧地嵌着一个“田”字</span></p>', 15, '2022-04-14 21:38:46');

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
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `cost` VALUES (50, 47, 5000, '已缴');
INSERT INTO `cost` VALUES (51, 43, 200, '未缴');
INSERT INTO `cost` VALUES (52, 44, 200, '已缴');
INSERT INTO `cost` VALUES (53, 48, 2, '未缴');
INSERT INTO `cost` VALUES (54, 49, 5000, '已缴');

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
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guarder
-- ----------------------------
INSERT INTO `guarder` VALUES (1, 'zhangsan', '123456', '张三', 30, '男', '13547895542', 2, '123');
INSERT INTO `guarder` VALUES (4, 'wang', 'f710eb42270a01fc4899883c4b046a51', '李四', 30, '男', '13458798541', 2, 'fR)@MAOV');
INSERT INTO `guarder` VALUES (5, 'zhaoliu', '66a5756456cf9647cfe73fea399209f7', '赵六', 36, '女', '13245879541', 2, '3%6CmlDL');
INSERT INTO `guarder` VALUES (7, 'wangwu', '3ad3731a2c09b2dea4782b67ca3cc3b0', '王五', 100, '男', '123', 2, '#&fyvLhZ');
INSERT INTO `guarder` VALUES (8, 'dalao', '473e2d124bc403546f0d0c3d4e186540', '刘哥', 123, '男', '123', 2, '29du^aC&');
INSERT INTO `guarder` VALUES (10, 'jianhuren', 'b22db339680fba9514e917d2a8839d84', '追梦人', 100, '男', '123', 2, '^4Hw4mgb');
INSERT INTO `guarder` VALUES (11, 'test', '4b5dfba939efc6658b7c9e3c186febd6', '六六', 25, '女', '123', 2, 'i3Rw5qWw');
INSERT INTO `guarder` VALUES (12, 'test2', '89d54ef6b6a6514cec08b4450591ad20', '小蓝', 123, '男', '123', 2, '4C2f#MIa');
INSERT INTO `guarder` VALUES (14, 'liuliu', 'f31f1540fddf57d73640bd97b9008353', '小小', NULL, NULL, NULL, 2, 'DOV#PKiv');
INSERT INTO `guarder` VALUES (15, 'jianhuren2', '19c73f52728fc170a720762d35e205fa', '小红', 100, '女', '123', 2, 'UzSyFnbq');
INSERT INTO `guarder` VALUES (16, 'jianhuaa', 'e4fbf94d1052bf2386e05c534a8527ba', '小美', 100, '女', '321', 2, 'DMoVfhy8');
INSERT INTO `guarder` VALUES (17, 'xiaolaodi', '956925f598af18e0a9e0f0da9522f581', '小大弟', NULL, NULL, NULL, 2, '0FBUU9vm');
INSERT INTO `guarder` VALUES (18, 'jianhu', '5aa429132dde9aaaf7f0dc744a39ee59', '张大哥', 30, '男', '13569874587', 2, 'hS)PVGnI');

-- ----------------------------
-- Table structure for health
-- ----------------------------
DROP TABLE IF EXISTS `health`;
CREATE TABLE `health`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `healthName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health
-- ----------------------------
INSERT INTO `health` VALUES (1, '健康', '老人身体健康');
INSERT INTO `health` VALUES (2, '观察', '老人身体需要观察');
INSERT INTO `health` VALUES (3, '紧急', '老人身体急需要进行相应的措施');

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
  `healthId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '健康情况：健康、观察和紧急',
  `enterDate` datetime(0) NULL DEFAULT NULL COMMENT '入住时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of older
-- ----------------------------
INSERT INTO `older` VALUES (1, '张大爷', '103', '13828528428', 18, '1', NULL);
INSERT INTO `older` VALUES (2, '李大爷', '101', '13828528422', 18, '1', NULL);
INSERT INTO `older` VALUES (3, '王大哥', '100', '13828528421', 2, '1', NULL);
INSERT INTO `older` VALUES (4, '黄阿姨', '100', '13828528423', 2, '1', NULL);
INSERT INTO `older` VALUES (5, '林大爷', '100', '13828528424', 4, '1', NULL);
INSERT INTO `older` VALUES (6, '陈阿姨', '100', '13828528425', 4, '1', NULL);
INSERT INTO `older` VALUES (7, '杨阿姨', '100', '13828528426', 18, '1', NULL);
INSERT INTO `older` VALUES (8, '勇大爷', '100', '13828528427', 18, '1', NULL);
INSERT INTO `older` VALUES (9, '胡老三', '100', '13828528429', 7, '1', '2021-10-01 01:58:16');
INSERT INTO `older` VALUES (10, '孙大哥', '100', '13828528418', 3, '1', '2021-11-01 01:58:12');
INSERT INTO `older` VALUES (11, '赵老', '100', '13828528438', 2, '1', '2021-04-01 01:58:10');
INSERT INTO `older` VALUES (12, '周老', '100', '13828528448', 1, '1', '2021-12-01 01:58:06');
INSERT INTO `older` VALUES (13, '吴老哥', '100', '13828528458', 3, '1', '2022-01-29 01:58:03');
INSERT INTO `older` VALUES (14, '刘大哥', '100', '13828528468', 4, '1', '2022-02-28 01:57:58');
INSERT INTO `older` VALUES (15, '余老哥', '100', '13828528478', 5, '1', '2021-02-01 01:57:48');
INSERT INTO `older` VALUES (16, '何老', '100', '13828528488', 6, '1', '2022-02-28 01:57:44');
INSERT INTO `older` VALUES (17, '宋老哥', '100', '13828528498', 7, '1', '2021-03-29 01:57:38');
INSERT INTO `older` VALUES (18, '马大哥', '100', '13828528408', 8, '1', '2021-05-29 01:57:21');
INSERT INTO `older` VALUES (19, '罗老', '100', '13828528128', 7, '1', '2021-05-29 01:57:15');
INSERT INTO `older` VALUES (20, '丁老', '100', '13828528228', 9, '1', '2021-06-29 01:57:08');
INSERT INTO `older` VALUES (21, '容老哥', '100', '13828528328', 6, '1', '2021-07-01 01:57:01');
INSERT INTO `older` VALUES (22, '献老哥', '100', '13828528428', 4, '2', '2022-02-01 01:56:58');
INSERT INTO `older` VALUES (23, '范大佬', '100', '13828528528', 9, '2', '2022-02-01 01:56:52');
INSERT INTO `older` VALUES (25, '张阿姨', '100', '13828528628', 11, '2', '2021-08-05 01:56:38');
INSERT INTO `older` VALUES (29, '丁阿姨', '100', NULL, 7, '3', '2021-08-01 01:56:32');
INSERT INTO `older` VALUES (30, '何大哥', '100', '123456789', 11, '3', '2021-09-01 01:56:26');
INSERT INTO `older` VALUES (31, '孙阿姨', '100', '123', 11, '1', '2022-02-01 01:56:21');
INSERT INTO `older` VALUES (33, '余阿姨', '100', '13', 12, '1', '2021-10-01 01:56:14');
INSERT INTO `older` VALUES (34, '林阿姨', '100', '123', 12, '2', '2021-10-01 01:56:09');
INSERT INTO `older` VALUES (35, '翁大哥', '100', '123', 12, '1', '2021-10-01 01:56:03');
INSERT INTO `older` VALUES (36, '庄大哥', '100', '123', 12, '1', '2021-11-01 01:55:57');
INSERT INTO `older` VALUES (37, '胡阿姨', '100', '123', 11, '2', '2021-12-01 01:55:52');
INSERT INTO `older` VALUES (38, '沈大哥', '100', '123', 11, '2', '2021-12-01 01:55:44');
INSERT INTO `older` VALUES (41, '翠阿姨', '130', '12211', 11, '2', '2022-02-02 01:55:36');
INSERT INTO `older` VALUES (43, '刘宝', NULL, NULL, 1, '3', '2022-02-01 00:00:00');
INSERT INTO `older` VALUES (44, '三姐', NULL, NULL, 1, '3', '2021-01-29 00:00:00');
INSERT INTO `older` VALUES (45, '李老', '100', '333', 1, '1', '2022-01-29 00:00:00');
INSERT INTO `older` VALUES (46, '娟阿姨', '100', '4654', 1, '1', '2022-01-29 00:00:00');
INSERT INTO `older` VALUES (48, '尹大哥', '100', '123', 1, '2', '2022-02-16 00:00:00');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `costId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `orders` VALUES (26, 'a687aecbf98e4483b35978cbaaadfd88', 2);
INSERT INTO `orders` VALUES (27, '27c514eddcc74a7d9ff17cd1348cdd08', 2);
INSERT INTO `orders` VALUES (28, '362ada53f52d43e0b91aa252074dcecf', 2);
INSERT INTO `orders` VALUES (29, '9bd006217c19439baf47948c272d97d3', 2);
INSERT INTO `orders` VALUES (30, '5ec12b4c0b884883b912e38c7988d110', 2);
INSERT INTO `orders` VALUES (31, '3fd21641e39848739ff39f8fa3429a3e', 2);
INSERT INTO `orders` VALUES (32, 'b689d84f6ad54fefa0b10214c594d381', 2);
INSERT INTO `orders` VALUES (33, 'b0169e6a94364e658e455e84fe2e16cd', 2);
INSERT INTO `orders` VALUES (34, '8709b48cd0a94532bab5c55c8cbfd273', 2);
INSERT INTO `orders` VALUES (35, 'a57a7022c1e84f6d8d84b206dd8450ab', 2);
INSERT INTO `orders` VALUES (36, '261fd7d59a694c9788c485ee11d32ae5', 2);
INSERT INTO `orders` VALUES (37, '949bfdbd9651452987b1cc8281bc1eed', 2);
INSERT INTO `orders` VALUES (38, '90165bd858ff44fea19c6df8cd5d3239', 2);
INSERT INTO `orders` VALUES (39, 'c9161bb94d514de4995bd273cc376de0', 2);
INSERT INTO `orders` VALUES (40, '2f5f3f08fb754eefb34930d819cf92ae', 2);
INSERT INTO `orders` VALUES (41, '6435be7918774a03918a5b9d8cd8e2e9', 2);
INSERT INTO `orders` VALUES (42, '26def1e133b34b1da33130aa3d44cd79', 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (2, 'test', 'fec2798e18b7e42a07eb2b1e038fe263', '贝贝', 3, 'G*3is(U4', 23, '男', '123');
INSERT INTO `staff` VALUES (4, 'yihu', '72d9ead984cf67b40fdf07e3ac2bc9d0', '小宝', 3, 'NFV8#DUt', 22, '男', '13247795620');
INSERT INTO `staff` VALUES (5, 'yihu2', '2ef51ff6c6c2f3a5f7aa1a4663705f7f', '豆豆', 3, 'HG(*jWPk', 22, '女', '13547895640');
INSERT INTO `staff` VALUES (6, 'yihu3', '62cb35b012bcababf0451cdaea80bdc0', '甜甜', 3, '$oGSmTWy', 33, '男', '1033611821');
INSERT INTO `staff` VALUES (7, 'yihu4', 'eb53999db5962c7396b35f5242c8ab29', '洋洋', 3, '^*7j5E)2', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (8, 'yihu5', '352ed6e8e1f16c307f5c27d19331a3a2', '小雨', 3, 'g6SvW*c(', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (9, 'yihu6', 'c56f28adbe219cbe3908167ad401b9d9', '涵涵', 3, '&2Jklrpq', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (11, 'yihu7', '2c1ad6e887b76a3370d00f5bd7d46b69', '然然', 3, 'gEfHSCQ6', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (12, 'yihu8', '75368e3d81e9abedd3cce384d9e9da44', '昊昊', 3, 'l(fQ%&I(', 100, '男', '123');
INSERT INTO `staff` VALUES (13, 'yihu9', '426ef218d1dc329f0395f463cccd193e', '佳佳', 3, 'MuYKhCh2', NULL, NULL, NULL);
INSERT INTO `staff` VALUES (15, 'yihu10', 'cc29223b26690da01dbda1ac1ad0e01e', '龙龙', 3, '#gT9HV6N', 100, '女', '123');
INSERT INTO `staff` VALUES (17, 'jianhu13', 'a035a75d2a83fb53029a563a0f9163a0', '宸宸', 3, 'np!GeARV', 100, '女', '321');
INSERT INTO `staff` VALUES (18, 'jianhu14', 'eafd1fc7e29327cdce4108cb1ea29a9c', '文文', 3, 'JK6SBCbP', 100, '女', '123');
INSERT INTO `staff` VALUES (19, 'yihu11', '5b1bb1894fa2aaf9e74975f05bff7937', '可可', 3, '(zLaEVau', 100, '女', '321');
INSERT INTO `staff` VALUES (20, 'yihu12', '8019a9f9191eefe6d45fc1f36cdc0017', '久久', 3, 'L@Ap8VrQ', 100, '女', '123');
INSERT INTO `staff` VALUES (21, 'yihuaa', '13575800aa1b09d2de42739bffe88cc9', '可心', 3, '5BS#SbmD', 100, '女', '321');

SET FOREIGN_KEY_CHECKS = 1;
