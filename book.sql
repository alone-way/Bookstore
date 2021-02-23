/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 23/02/2021 15:03:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(11, 2) NULL DEFAULT NULL,
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sales` int NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `img_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (23, 'java从入门到放弃', 80.00, '国哥', 10000, 8, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (24, '数据结构与算法', 78.50, '严敏君', 7, 12, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (25, '怎样拐跑别人的媳妇', 68.00, '龙伍', 100002, 49, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (26, '木虚肉盖饭', 16.00, '小胖', 1004, 46, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (27, 'C++编程思想', 45.50, '刚哥', 14, 95, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (28, '蛋炒饭', 9.90, '周星星', 13, 52, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (29, '赌神', 66.50, '龙伍', 127, 533, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (30, 'Java编程思想', 99.50, '阳哥', 47, 36, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (31, 'JavaScript从入门到精通', 9.90, '婷姐', 85, 95, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (32, 'cocos2d-x游戏编程入门', 49.00, '国哥', 52, 62, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (33, 'C语言程序设计', 28.00, '谭浩强', 52, 74, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (34, 'Lua语言程序设计', 51.50, '雷丰阳', 48, 82, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (36, '水浒传', 33.05, '华仔', 22, 88, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (37, '操作系统原理', 133.05, '刘优', 122, 188, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (38, '数据结构 java版', 173.15, '封大神', 21, 81, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (39, 'UNIX高级环境编程', 99.15, '乐天', 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (40, 'javaScript高级编程', 69.15, '国哥', 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (41, '大话设计模式', 89.15, '国哥', 20, 10, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (42, '人月神话', 88.15, '刚哥', 20, 80, 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `create_time` datetime(0) NULL DEFAULT '2020-01-01 00:00:00' COMMENT '创建时间',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `status` tinyint NULL DEFAULT NULL COMMENT '订单状态,0未发货,1已发货,2已收货',
  `user_id` int NULL DEFAULT NULL COMMENT '所属的用户id',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('16140626483634', '2021-02-23 14:44:08', 112.00, 1, 4);
INSERT INTO `t_order` VALUES ('16140626622854', '2021-02-23 14:44:22', 162.50, 1, 4);
INSERT INTO `t_order` VALUES ('16140626686444', '2021-02-23 14:44:29', 133.00, 1, 4);
INSERT INTO `t_order` VALUES ('16140626778134', '2021-02-23 14:44:38', 9.90, 0, 4);
INSERT INTO `t_order` VALUES ('16140631911404', '2021-02-23 14:53:11', 68.00, 0, 4);
INSERT INTO `t_order` VALUES ('16140631948654', '2021-02-23 14:53:15', 68.00, 1, 4);
INSERT INTO `t_order` VALUES ('16140631984954', '2021-02-23 14:53:18', 16.00, 1, 4);

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单项id(主键)',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `count` int NULL DEFAULT NULL COMMENT '商品数量',
  `price` decimal(11, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `total_price` decimal(11, 2) NULL DEFAULT NULL COMMENT '商品总价',
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属的订单id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES (88, 'java从入门到放弃', 1, 80.00, 80.00, '16140626483634');
INSERT INTO `t_order_item` VALUES (89, '木虚肉盖饭', 2, 16.00, 32.00, '16140626483634');
INSERT INTO `t_order_item` VALUES (90, '数据结构与算法', 1, 78.50, 78.50, '16140626622854');
INSERT INTO `t_order_item` VALUES (91, '怎样拐跑别人的媳妇', 1, 68.00, 68.00, '16140626622854');
INSERT INTO `t_order_item` VALUES (92, '木虚肉盖饭', 1, 16.00, 16.00, '16140626622854');
INSERT INTO `t_order_item` VALUES (93, '赌神', 2, 66.50, 133.00, '16140626686444');
INSERT INTO `t_order_item` VALUES (94, '蛋炒饭', 1, 9.90, 9.90, '16140626778134');
INSERT INTO `t_order_item` VALUES (95, '怎样拐跑别人的媳妇', 1, 68.00, 68.00, '16140631911404');
INSERT INTO `t_order_item` VALUES (96, '怎样拐跑别人的媳妇', 1, 68.00, 68.00, '16140631948654');
INSERT INTO `t_order_item` VALUES (97, '木虚肉盖饭', 1, 16.00, 16.00, '16140631984954');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (4, 'admin', 'admin', 'admin@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
