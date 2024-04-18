/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50540 (5.5.40)
 Source Host           : localhost:3306
 Source Schema         : springboot_demo

 Target Server Type    : MySQL
 Target Server Version : 50540 (5.5.40)
 File Encoding         : 65001

 Date: 10/04/2024 10:33:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'path',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `parentId` int(11) NULL DEFAULT NULL COMMENT '父id',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parentId`(`parentId`) USING BTREE,
  CONSTRAINT `menu_fk_1` FOREIGN KEY (`parentId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '/', '/home', 'Home', '信息管理', 'el-icon-menu', NULL, 1);
INSERT INTO `t_menu` VALUES (2, '/', '/home', 'Home', 'ECharts图表', 'el-icon-menu', NULL, 1);
INSERT INTO `t_menu` VALUES (3, '/info/**', '/info/person', 'InfoPerson', '人员信息', 'NULL', 1, 1);
INSERT INTO `t_menu` VALUES (4, '/echarts/**', '/echarts/bar', 'EchartsBar', '柱状图', 'NULL', 2, 1);
INSERT INTO `t_menu` VALUES (5, '/echarts/**', '/echarts/pie', 'EchartsPie', '饼图', 'NULL', 2, 1);
INSERT INTO `t_menu` VALUES (6, '/echarts/**', '/echarts/data_screen', 'EchartsDataScreen', '数据大屏', 'NULL', 2, 1);

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '人员id',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `idCard` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `provinceId` int(8) NULL DEFAULT NULL COMMENT '省份id',
  `gender` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(8) NULL DEFAULT NULL COMMENT '年龄',
  `birth` date NULL DEFAULT NULL COMMENT '出生日期',
  `weight` int(8) NULL DEFAULT NULL COMMENT '体重',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `provinceId`(`provinceId`) USING BTREE,
  CONSTRAINT `t_person_fk_1` FOREIGN KEY (`provinceId`) REFERENCES `t_province` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES (1, '舒影', '341002201607052644', 2, '女', 7, '2016-07-05', 40);
INSERT INTO `t_person` VALUES (2, '项彬', '610624193207118112', 14, '男', 91, '1932-07-11', 47);
INSERT INTO `t_person` VALUES (3, '缑滢', '150221196401173985', 5, '女', 60, '1964-01-17', 92);
INSERT INTO `t_person` VALUES (4, '尤友', '230206201512129055', 3, '男', 8, '2015-12-12', 90);
INSERT INTO `t_person` VALUES (5, '铁达', '640424194902145832', 16, '男', 75, '1949-02-14', 70);
INSERT INTO `t_person` VALUES (6, '齐树', '420821200608158552', 7, '男', 17, '2006-08-15', 68);
INSERT INTO `t_person` VALUES (7, '梁丘岚', '450521201605230380', 3, '女', 7, '2016-05-23', 84);
INSERT INTO `t_person` VALUES (8, '蓟寒', '152201194802050385', 3, '女', 76, '1948-02-05', 51);
INSERT INTO `t_person` VALUES (9, '浑哲', '632324195805039076', 19, '男', 65, '1958-05-03', 67);
INSERT INTO `t_person` VALUES (10, '东方春', '370911197908268829', 2, '女', 44, '1979-08-26', 48);
INSERT INTO `t_person` VALUES (11, '畅才', '140932195611061915', 12, '男', 67, '1956-11-06', 47);
INSERT INTO `t_person` VALUES (12, '元希', '341503200108178647', 1, '女', 22, '2001-08-17', 86);
INSERT INTO `t_person` VALUES (13, '巩贵', '321111199108139057', 9, '男', 32, '1991-08-13', 62);
INSERT INTO `t_person` VALUES (14, '喻仁', '370883198912263634', 18, '男', 34, '1989-12-26', 77);
INSERT INTO `t_person` VALUES (15, '华馥', '500224199911254425', 17, '女', 24, '1999-11-25', 48);
INSERT INTO `t_person` VALUES (16, '怀爱', '632222198709228163', 18, '女', 36, '1987-09-22', 99);
INSERT INTO `t_person` VALUES (17, '扈民', '530128201102055050', 9, '男', 13, '2011-02-05', 89);
INSERT INTO `t_person` VALUES (18, '詹毅', '63252519610307301X', 23, '男', 63, '1961-03-07', 93);
INSERT INTO `t_person` VALUES (19, '项丽', '441622198502019626', 12, '女', 39, '1985-02-01', 71);
INSERT INTO `t_person` VALUES (20, '阮栋', '623026193906136613', 3, '男', 84, '1939-06-13', 64);
INSERT INTO `t_person` VALUES (21, '禄博', '211100192902184959', 4, '男', 95, '1929-02-18', 78);
INSERT INTO `t_person` VALUES (22, '楚勤', '350502194501246905', 7, '女', 79, '1945-01-24', 66);
INSERT INTO `t_person` VALUES (23, '支成', '441422196407316059', 23, '男', 59, '1964-07-31', 82);
INSERT INTO `t_person` VALUES (24, '伍胜', '44051419270830607X', 5, '男', 96, '1927-08-30', 91);
INSERT INTO `t_person` VALUES (25, '丁贞', '431002198509305326', 15, '女', 38, '1985-09-30', 93);
INSERT INTO `t_person` VALUES (26, '红林', '15252220170217447X', 7, '男', 7, '2017-02-17', 60);
INSERT INTO `t_person` VALUES (27, '林固', '230111192404263111', 1, '男', 99, '1924-04-26', 68);
INSERT INTO `t_person` VALUES (28, '禹行', '13048119490812807X', 16, '男', 74, '1949-08-12', 75);
INSERT INTO `t_person` VALUES (29, '全琛', '431300194008123693', 15, '男', 83, '1940-08-12', 42);
INSERT INTO `t_person` VALUES (30, '有浩', '43048119531126789X', 13, '男', 70, '1953-11-26', 81);
INSERT INTO `t_person` VALUES (31, '邰新', '340421193408038076', 20, '男', 89, '1934-08-03', 44);
INSERT INTO `t_person` VALUES (32, '班波', '211200200203109434', 5, '男', 22, '2002-03-10', 49);
INSERT INTO `t_person` VALUES (33, '哈寒', '321088195503055101', 14, '女', 69, '1955-03-05', 65);
INSERT INTO `t_person` VALUES (34, '巫马言', '411724199401082359', 2, '男', 30, '1994-01-08', 50);
INSERT INTO `t_person` VALUES (35, '洪河', '650205197104106578', 20, '男', 52, '1971-04-10', 85);
INSERT INTO `t_person` VALUES (36, '恽娜', '371424199412246807', 5, '女', 29, '1994-12-24', 82);
INSERT INTO `t_person` VALUES (37, '崔红', '371002200211202029', 19, '女', 21, '2002-11-20', 54);
INSERT INTO `t_person` VALUES (38, '隗安', '530827198701097516', 13, '男', 37, '1987-01-09', 95);
INSERT INTO `t_person` VALUES (39, '和翔', '652801196905292897', 4, '男', 54, '1969-05-29', 68);
INSERT INTO `t_person` VALUES (40, '边融', '542229194004139747', 3, '女', 83, '1940-04-13', 41);
INSERT INTO `t_person` VALUES (41, '常春', '451226201509097168', 4, '女', 8, '2015-09-09', 50);
INSERT INTO `t_person` VALUES (42, '苏飞', '140928202201183256', 23, '男', 2, '2022-01-18', 71);
INSERT INTO `t_person` VALUES (43, '贝言', '350430199601071172', 1, '男', 28, '1996-01-07', 53);
INSERT INTO `t_person` VALUES (44, '郦炎', '522300199009074876', 15, '男', 33, '1990-09-07', 75);
INSERT INTO `t_person` VALUES (45, '步寒', '500117193704046184', 17, '女', 86, '1937-04-04', 80);
INSERT INTO `t_person` VALUES (46, '宿佳', '653227194002033846', 8, '女', 84, '1940-02-03', 54);
INSERT INTO `t_person` VALUES (47, '葛辉', '361025199106050657', 13, '男', 32, '1991-06-05', 71);
INSERT INTO `t_person` VALUES (48, '田亮', '450502198907100318', 5, '男', 34, '1989-07-10', 43);
INSERT INTO `t_person` VALUES (49, '骆慧', '451202200111099248', 11, '女', 22, '2001-11-09', 56);
INSERT INTO `t_person` VALUES (50, '费锦', '610923198312284107', 9, '女', 40, '1983-12-28', 79);

-- ----------------------------
-- Table structure for t_province
-- ----------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_province
-- ----------------------------
INSERT INTO `t_province` VALUES (1, '河北省');
INSERT INTO `t_province` VALUES (2, '山西省');
INSERT INTO `t_province` VALUES (3, '辽宁省');
INSERT INTO `t_province` VALUES (4, '吉林省');
INSERT INTO `t_province` VALUES (5, '黑龙江省');
INSERT INTO `t_province` VALUES (6, '江苏省');
INSERT INTO `t_province` VALUES (7, '浙江省');
INSERT INTO `t_province` VALUES (8, '安徽省');
INSERT INTO `t_province` VALUES (9, '福建省');
INSERT INTO `t_province` VALUES (10, '江西省');
INSERT INTO `t_province` VALUES (11, '山东省');
INSERT INTO `t_province` VALUES (12, '河南省');
INSERT INTO `t_province` VALUES (13, '广东省');
INSERT INTO `t_province` VALUES (14, '湖南省');
INSERT INTO `t_province` VALUES (15, '湖北省');
INSERT INTO `t_province` VALUES (16, '海南省');
INSERT INTO `t_province` VALUES (17, '四川省');
INSERT INTO `t_province` VALUES (18, '贵州省');
INSERT INTO `t_province` VALUES (19, '云南省');
INSERT INTO `t_province` VALUES (20, '陕西省');
INSERT INTO `t_province` VALUES (21, '甘肃省');
INSERT INTO `t_province` VALUES (22, '青海省');
INSERT INTO `t_province` VALUES (23, '台湾省');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '管理员', '12345678900', '福建省福州市', 1, 'admin', '$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe', NULL);

SET FOREIGN_KEY_CHECKS = 1;
