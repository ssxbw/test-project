/*
 Navicat Premium Data Transfer

 Source Server         : multydoffer
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : payment

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 11/06/2023 23:38:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for abnormal_transactions
-- ----------------------------
DROP TABLE IF EXISTS `abnormal_transactions`;
CREATE TABLE `abnormal_transactions`  (
                                          `transaction_id` int NOT NULL,
                                          `user_id` int NOT NULL,
                                          `seller_id` int NOT NULL,
                                          `order_id` int NOT NULL,
                                          `amount` decimal(8, 2) NOT NULL,
                                          `time` datetime NULL DEFAULT NULL,
                                          PRIMARY KEY (`transaction_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of abnormal_transactions
-- ----------------------------
INSERT INTO `abnormal_transactions` VALUES (4020, 4001, 20, 4008, -30.00, '2023-05-31 03:00:45'),
                                           (4026, 4006, 20, 4009, -200000,'2023-05-31 05:00:45');

-- ----------------------------
-- Table structure for account_backup
-- ----------------------------
DROP TABLE IF EXISTS `account_backup`;
CREATE TABLE `account_backup`  (
                                   `user_id` int NOT NULL,
                                   `date` date NOT NULL,
                                   `balance` decimal(8, 2) NOT NULL,
                                   `state` int NOT NULL,
                                   `cutoff` time NULL DEFAULT NULL,
                                   `charge` decimal(8, 2) NOT NULL,
                                   PRIMARY KEY (`user_id`, `date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account_backup
-- ----------------------------
INSERT INTO `account_backup` VALUES (4001, '2023-05-30', 40.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4001, '2023-05-31', 50.00, 1, NULL, 10.00);
INSERT INTO `account_backup` VALUES (4002, '2023-05-30', 200.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4002, '2023-05-31', 100.00, 0, NULL, 20.00);
INSERT INTO `account_backup` VALUES (4003, '2023-05-30', 200.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4003, '2023-05-31', 200.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4004, '2023-05-30', 95.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4004, '2023-05-31', 75.00, 0, '01:00:00', 15.00);
INSERT INTO `account_backup` VALUES (4005, '2023-05-30', 75.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4005, '2023-05-31', 75.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4006, '2023-05-30', 200075.00, 0, NULL, 0.00);
INSERT INTO `account_backup` VALUES (4006, '2023-05-31', 75.00, 2, NULL, 0.00);

-- ----------------------------
-- Table structure for arbitration_record
-- ----------------------------
DROP TABLE IF EXISTS `arbitration_record`;
CREATE TABLE `arbitration_record`  (
                                       `arbitration_id` int NOT NULL AUTO_INCREMENT,
                                       `arbitrated_user_id` int NULL DEFAULT NULL,
                                       `arbitration_time` bigint NULL DEFAULT 0,
                                       `start_time` bigint NULL DEFAULT 0,
                                       `end_time` bigint NULL DEFAULT 0,
                                       PRIMARY KEY (`arbitration_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arbitration_record
-- ----------------------------
INSERT INTO `arbitration_record` VALUES (1001, 1004, 6000000, 20230609000000, 20230615000000);

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES (1001, 'card1', '123456');
INSERT INTO `card` VALUES (1002, 'card2', '123456');

-- ----------------------------
-- Table structure for common_account
-- ----------------------------
DROP TABLE IF EXISTS `common_account`;
CREATE TABLE `common_account`  (
                                   `order_id` int NOT NULL AUTO_INCREMENT,
                                   `total_price` decimal(8, 2) NULL DEFAULT NULL,
                                   PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_account
-- ----------------------------
INSERT INTO `common_account` VALUES (2102, 139.00);
INSERT INTO `common_account` VALUES (2103, 2999.00);
INSERT INTO `common_account` VALUES (2104, 318.00);
INSERT INTO `common_account` VALUES (2106, 116.00);

-- ----------------------------
-- Table structure for elk_file
-- ----------------------------
DROP TABLE IF EXISTS `elk_file`;
CREATE TABLE `elk_file`  (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                             `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                             `new_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                             `old_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                             `doc_id` int NULL DEFAULT NULL,
                             `is_del` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0',
                             `is_elk` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0',
                             `file_size` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件大小',
                             `pdfurl` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT 'pdfurl路径',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of elk_file
-- ----------------------------
INSERT INTO `elk_file` VALUES (66, 'http://localhost:80/payment/images/7debfb70-f4eb-4665-9732-95e957038225.png', 'HG.png', '7debfb70-f4eb-4665-9732-95e957038225.png', 'HG.png', NULL, '0', '0', '17828', NULL);
INSERT INTO `elk_file` VALUES (99, 'http://localhost:80/payment/images/5af516a4-ef0d-4875-a062-aa61c92b2cc2.png', 'tz_.png', '5af516a4-ef0d-4875-a062-aa61c92b2cc2.png', 'tz_.png', NULL, '0', '0', '88406', NULL);

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
                           `flight_id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `flight_number` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '航班号',
                           `departure_time` datetime NULL DEFAULT NULL COMMENT '出发时间',
                           `arrival_time` datetime NULL DEFAULT NULL COMMENT '到达时间',
                           `departure_airport` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '出发机场',
                           `arrival_airport` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '到达机场',
                           `min_price` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '最低价格',
                           `discount` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否折扣',
                           `company` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '公司名称',
                           `fileIds` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片',
                           PRIMARY KEY (`flight_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20009 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '航班' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES (1001, 'HX312', '2023-05-30 15:55:00', '2023-05-30 19:05:00', '香港国际机场T1', '首都国际机场T2', '1181.00', 'N', '香港航空', NULL);
INSERT INTO `flight` VALUES (1004, 'CZ6782', '2023-05-30 20:50:00', '2023-05-30 23:50:00', '上海浦东国际机场T2', '三亚凤凰国际机场T1', '289.00', 'N', '南方航空', NULL);
INSERT INTO `flight` VALUES (20000, 'HX312', '2023-06-09 15:55:00', '2023-05-30 19:05:00', '香港国际机场T1', '首都国际机场T2', '1181.0', 'N', '香港航空', '99');
INSERT INTO `flight` VALUES (20003, 'CZ6782', '2023-06-11 20:50:00', '2023-05-30 23:50:00', '上海浦东国际机场T2', '三亚凤凰国际机场T1', '289.0', 'N', '南方航空', '99');
INSERT INTO `flight` VALUES (20006, 'HO1379', '2023-06-10 08:05:00', '2023-05-30 12:10:00', '上海浦东国际机场T2', '成田国际机场T2', '1521.0', 'Y', '均瑶航空', '99');

-- ----------------------------
-- Table structure for flight_class
-- ----------------------------
DROP TABLE IF EXISTS `flight_class`;
CREATE TABLE `flight_class`  (
                                 `class_id` int NOT NULL AUTO_INCREMENT COMMENT '舱位id',
                                 `flight_id` int NULL DEFAULT NULL COMMENT '航班id',
                                 `cabin` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客舱',
                                 `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
                                 `discount` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否折扣',
                                 `fileIds` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                                 `stock_0` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_1` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_2` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_3` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_4` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_5` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_6` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_7` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_8` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_9` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_10` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_11` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_12` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_13` int NULL DEFAULT NULL COMMENT '余量',
                                 `stock_14` int NULL DEFAULT NULL COMMENT '余量',
                                 PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2018 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '舱位表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flight_class
-- ----------------------------
INSERT INTO `flight_class` VALUES (8, 20000, '商务舱', 6913.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (11, 20003, '经济舱', 289.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (14, 20003, '商务舱', 450.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (17, 20006, '经济舱', 1521.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (20, 20006, '商务舱', 5513.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2007, 20006, '测试', 99.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2009, 20000, '测试', 100.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2010, 20000, 'ce', 1.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2011, 20000, '1', 1.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2014, 20000, '测试', 11.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2017, 20000, '测试', 100.00, 'Y', '99', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
                          `hotel_id` int NOT NULL,
                          `rating` decimal(3, 2) NULL DEFAULT NULL,
                          `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                          `discount` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'N',
                          `star` int NOT NULL,
                          `min_price` decimal(8, 2) NOT NULL,
                          `fileIds` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                          PRIMARY KEY (`hotel_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10029 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '酒店表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1000, 5.00, '水印公寓', '余杭塘路与蒋墩路交汇处绿城西溪世纪中心4号楼1楼', 'Y', 5, 191.20, NULL);
INSERT INTO `hotel` VALUES (1003, 4.60, '浙江大学圆正·启真酒店', '余杭塘路866号浙大紫金港校区内', 'N', 5, 939.00, NULL);
INSERT INTO `hotel` VALUES (1006, 4.90, '轻住·梦之舟精品LOFT酒店', '郎悦居1幢4单元217室', 'N', 3, 245.00, NULL);
INSERT INTO `hotel` VALUES (10000, 1.00, '水印公寓', '余杭塘路与蒋墩路交汇处绿城西溪世纪中心4号楼1楼', 'Y', 5, 191.20, NULL);
INSERT INTO `hotel` VALUES (10003, 4.60, '浙江大学圆正·启真酒店', '余杭塘路866号浙大紫金港校区内', 'N', 5, 939.00, NULL);
INSERT INTO `hotel` VALUES (10006, 4.90, '轻住·梦之舟精品LOFT酒店', '郎悦居1幢4单元217室', 'N', 3, 245.00, NULL);
INSERT INTO `hotel` VALUES (10023, 1.00, '阳光酒店', '12', '0', 3, 100.00, '');
INSERT INTO `hotel` VALUES (10028, 2.00, '测试', '测试', '0', 3, 200.00, '99');

-- ----------------------------
-- Table structure for identity
-- ----------------------------
DROP TABLE IF EXISTS `identity`;
CREATE TABLE `identity`  (
                             `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             PRIMARY KEY (`id_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of identity
-- ----------------------------
INSERT INTO `identity` VALUES ('张三', '330181200304053333');
INSERT INTO `identity` VALUES ('李四', '330181200304053334');
INSERT INTO `identity` VALUES ('王五', '330181200304053335');
INSERT INTO `identity` VALUES ('赵六', '330181200304053337');
INSERT INTO `identity` VALUES ('孙七', '330181200304053336');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
                         `item_id` int NOT NULL AUTO_INCREMENT,
                         `item_description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                         `price` decimal(8, 2) NULL DEFAULT NULL,
                         `seller_id` int NOT NULL,
                         `count` int NOT NULL,
                         `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `image` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90016 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1002, '百草味零食', 99.00, 1003, 6, '蔓越莓夹心曲奇', '');
INSERT INTO `item` VALUES (1005, '平板电脑', 1999.00, 1003, 5, 'ipad air4', '');
INSERT INTO `item` VALUES (2001, '牙膏', 16.80, 2004, 1000, '云南白药牙膏', '/common/download?name=tb_image_share_1686469092655.jpg');
INSERT INTO `item` VALUES (2004, '零食', 89.00, 2004, 80, '三只松鼠零食大礼包', '/common/download?name=tb_image_share_1686469127194.jpg');
INSERT INTO `item` VALUES (2007, '笔记本电脑', 8999.00, 2004, 50, '拯救者R9000P', '/common/download?name=tb_image_share_1686469147587.jpg');
INSERT INTO `item` VALUES (2010, '(售罄)懒人沙发', 318.00, 2004, 0, 'lucksac懒人沙发豆', '/common/download?name=tb_image_share_1686468191936.jpg');
INSERT INTO `item` VALUES (2013, '外星人电脑', 19999.99, 2005, 50, '外星人m16', '/common/download?name=tb_image_share_1686469533703.jpg');
INSERT INTO `item` VALUES (2016, '机械键盘', 699.00, 2005, 100, 'ROG游侠RX', '/common/download?name=tb_image_share_1686469752710.jpg');
INSERT INTO `item` VALUES (2019, '限量版球鞋', 2999.00, 2005, 10, 'AJ11康扣', '/common/download?name=tb_image_share_1686470291516.jpg');
INSERT INTO `item` VALUES (2022, '头戴式耳机', 1699.00, 2005, 30, 'BoseQC45耳机', '/common/download?name=tb_image_share_1686470460824.jpg');
INSERT INTO `item` VALUES (2025, '精华水', 1370.00, 2004, 20, 'LAMER海蓝之谜精粹水', '/common/download?name=tb_image_share_1686471397562.jpg');
INSERT INTO `item` VALUES (2028, '文创', 139.00, 2004, 1000, '故宫金桂浮月杯子', '/common/download?name=tb_image_share_1686471661564.jpg');
INSERT INTO `item` VALUES (2031, '食物', 139.00, 2005, 10000, '麦当劳欢聚餐', '/common/download?name=tb_image_share_1686472645910.jpg');
INSERT INTO `item` VALUES (2034, '原神周边', 149.00, 2005, 1500, '太郎丸毛绒公仔', '/common/download?name=tb_image_share_1686472791795.jpg');
INSERT INTO `item` VALUES (2037, '明日方舟周边', 58.00, 2005, 2500, '朝陇山兔毛绒玩具', '/common/download?name=tb_image_share_1686472935147.jpg');

-- ----------------------------
-- Table structure for manager_info
-- ----------------------------
DROP TABLE IF EXISTS `manager_info`;
CREATE TABLE `manager_info`  (
                                 `manager_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                                 `manager_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `is_locked` tinyint(1) NULL DEFAULT NULL,
                                 `telephone_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `manager_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                 `lock_start_time` bigint NULL DEFAULT 0,
                                 `lock_duration` bigint NULL DEFAULT 0,
                                 `login_times` int NULL DEFAULT 0,
                                 PRIMARY KEY (`manager_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_info
-- ----------------------------
INSERT INTO `manager_info` VALUES ('alice1', '$2a$10$B2Exq0Q4P9Uex0fDbyJyWeIF8twFaIzE0L.SPvbENA/5kIp18qiPu', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('alice11', '$2a$10$odK4esw5GXnQ62/9VQJJGuuPk/RY5OxeXy2JGSHV6QXxyFX.gPTsu', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob1', '$2a$10$fKk/C5/vu.0CxNLDOwELOupe.3Kehm.M6H1x9DmMhkjjMKMXxSOda', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob11', '$2a$10$EI3vwJK7KivnFZVPQa4V4.ryU13Rkk21asP3fKYHdhG92wbLznwWS', 0, NULL, 'check', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob12', '$2a$10$wQUuTGLwyKGFPyZ5PxdHcuJBg1DZlrQsTP/TmbfqvrfcBri/OrWJ2', 0, NULL, 'system', 0, 0, 1);
INSERT INTO `manager_info` VALUES ('bob13', '$2a$10$RIxv6oXPOS/LH2a4RCn/.uBN7EiSy5NZxuvhb0zau7RTkUG7qqgI6', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob14', '$2a$10$qnNRIumhTw0d1uJ7OBehtO35FdLij5Y4jiPN2b5uafVo3gVnBsS4O', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob6', '$2a$10$leIUbENbEtg07.01aCXLsuvs6Vl11XyQR.cFz8Iiy1IgGFY55U67K', 0, NULL, 'check', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob7', '$2a$10$/cImQhPiUgJ7xpBeuZy92OMso0z0rW6IF3YJl.i58cKJ.80h9zr56', 0, NULL, 'check', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob8', '$2a$10$4xY3vMia71UsQ.M.bfQ9lOn3GwsoUHP1GUTG6puDjo3MXG0/FR3m6', 0, NULL, 'check', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('bob9', '$2a$10$5h7uhaSQMW4hh2f4vt1wDOY0Sw.lbWc3Iqf2xJSFgU4tdRzg9.ijO', 0, NULL, 'check', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike', '$2a$10$vK0XQOrliiFf4AYiXRKUVOxlW6CPrfIiyN0BjRdLgCIbmrjgglcLe', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike1', '$2a$10$/GhlmpSUutem75ZOI.bP3.kzZM0EUzMSkuQE3MwwgoLjsyxKVINIK', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike123', '$2a$10$Jz9P71FJxNyASVajN7CSh.lTtqsp2kdfc45fFVxGFozRFCmTHLyOu', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike2', '$2a$10$EGa3jk5uQAivDQeTd9L67e1zLWQp1z6P0iQJJ.5DAX5ZT1C6k8BvS', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike3', '$2a$10$mVbx2pW2YZX02cWoeX66se8SSXEOjA8QuIFiNrVBq.E5Ef.U4k24i', 0, NULL, 'book', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike4', '$2a$10$2JCzQh1z/99ujtOFC/dT3.t59X8ajq.nLd.5lnXUrfvrkKuPE1xoe', 0, NULL, 'book', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike5', '$2a$10$iNdw7gMrc2wCyfIG9mbCk.LF5bEcxei9zl8lLW3DCbt/IP6e9s2tC', 0, NULL, 'system', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('mike6', '$2a$10$zcT8153hmeXwTlBCIAZBo.tp6qWlSA9oLWQhXkRkm/Lb9P8jdwYO.', 0, NULL, 'book', 0, 0, 0);
INSERT INTO `manager_info` VALUES ('origin', '$2a$10$/SZjbGABVcbnwY7W4n/cHeYd3UdMn4M/cAuKocPWK26iowrdGplbK', 0, NULL, 'System', 0, 0, 0);

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge`  (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `user_id` int NOT NULL,
                             `amount` decimal(10, 2) NOT NULL,
                             `date` datetime NOT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES (1001, 2, 10.00, '2023-05-26 00:00:00');
INSERT INTO `recharge` VALUES (1002, 2, 10.00, '2023-05-26 00:00:00');
INSERT INTO `recharge` VALUES (1003, 2, 20.00, '2023-05-26 00:00:00');
INSERT INTO `recharge` VALUES (1004, 1, 12.00, '2023-06-08 13:14:08');
INSERT INTO `recharge` VALUES (1005, 1, 12.00, '2023-06-08 13:16:30');
INSERT INTO `recharge` VALUES (1006, 1, 20.00, '2023-06-08 13:39:28');
INSERT INTO `recharge` VALUES (4001, 4001, 10.00, '2023-06-01 23:30:20');
INSERT INTO `recharge` VALUES (4002, 4001, 10.00, '2023-06-01 00:30:20');
INSERT INTO `recharge` VALUES (4003, 4002, 10.00, '2023-06-01 23:30:20');
INSERT INTO `recharge` VALUES (4004, 4002, 10.00, '2023-06-01 23:30:20');
INSERT INTO `recharge` VALUES (4005, 4003, 10.00, '2023-06-01 23:30:20');
INSERT INTO `recharge` VALUES (4006, 4003, 10.00, '2023-06-01 23:30:20');
INSERT INTO `recharge` VALUES (4007, 4004, 15.00, '2023-06-01 00:00:18');
INSERT INTO `recharge` VALUES (4008, 4004, 10.00, '2023-06-01 02:00:18');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
                         `room_id` int NOT NULL COMMENT '房间id',
                         `hotel_id` int NULL DEFAULT NULL COMMENT '酒店id',
                         `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '房型',
                         `price` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '价格',
                         `discount` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '是否折扣',
                         `area` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '面积',
                         `stock_0` int NULL DEFAULT NULL COMMENT '余量1',
                         `stock_1` int NULL DEFAULT NULL COMMENT '余量2',
                         `stock_2` int NULL DEFAULT NULL COMMENT '余量3',
                         `stock_3` int NULL DEFAULT NULL COMMENT '余量4',
                         `stock_4` int NULL DEFAULT NULL COMMENT '余量5',
                         `stock_5` int NULL DEFAULT NULL COMMENT '余量6',
                         `stock_6` int NULL DEFAULT NULL COMMENT '余量7',
                         `stock_7` int NULL DEFAULT NULL COMMENT '余量8',
                         `stock_8` int NULL DEFAULT NULL COMMENT '余量9',
                         `stock_9` int NULL DEFAULT NULL COMMENT '余量10',
                         `stock_10` int NULL DEFAULT NULL COMMENT '余量11',
                         `stock_11` int NULL DEFAULT NULL COMMENT '余量12',
                         `stock_12` int NULL DEFAULT NULL COMMENT '余量13',
                         `stock_13` int NULL DEFAULT NULL COMMENT '余量14',
                         `stock_14` int NULL DEFAULT NULL COMMENT '余量15',
                         `fileIds` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片',
                         PRIMARY KEY (`room_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '酒店房间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (4, 10006, '精品商务大床房', '245.0', 'N', '45', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (7, 10006, '商务特色双人房', '271.0', 'N', '45', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (10, 10006, '精品商务双人房', '287.0', 'N', '30', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (13, 10003, '豪华标间', '939.0', 'N', '30', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (16, 10003, '豪华大床房', '384.0', 'N', '30', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (19, 10000, '特价大床房', '158.0', 'Y', '48', 3, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (22, 10000, '精选双床房', '289.0', 'N', '55', 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (25, 10000, '精选景观套房', '573.0', 'N', '102', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');
INSERT INTO `room` VALUES (28, 10023, '阳光房', '100', 'Y', '100', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '66');
INSERT INTO `room` VALUES (31, 10028, '测试', '10', 'Y', '100', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '66');
INSERT INTO `room` VALUES (34, 10028, '海景房', '200', 'Y', '100', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, '66');

-- ----------------------------
-- Table structure for transaction_flow
-- ----------------------------
DROP TABLE IF EXISTS `transaction_flow`;
CREATE TABLE `transaction_flow`  (
                                     `transaction_id` int NOT NULL AUTO_INCREMENT,
                                     `order_id` int NULL DEFAULT NULL,
                                     `order_state` int NULL DEFAULT NULL,
                                     `create_time` timestamp NULL DEFAULT NULL,
                                     `order_state_string` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                     PRIMARY KEY (`transaction_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4026 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of transaction_flow
-- ----------------------------
INSERT INTO `transaction_flow` VALUES (1001, 1001, 3, '2023-05-16 14:55:20', NULL);
INSERT INTO `transaction_flow` VALUES (1002, 1003, 3, '2023-05-17 12:17:32', NULL);
INSERT INTO `transaction_flow` VALUES (1003, 1003, 8, '2023-06-07 15:59:28', NULL);
INSERT INTO `transaction_flow` VALUES (1004, 1002, 3, '2023-06-10 15:59:28', NULL);
INSERT INTO `transaction_flow` VALUES (1005, 1004, 5, '2023-06-11 10:09:28', NULL);
INSERT INTO `transaction_flow` VALUES (2501, 2100, 3, '2023-05-01 15:11:23', NULL);
INSERT INTO `transaction_flow` VALUES (2502, 2100, 5, '2023-05-02 09:33:43', NULL);
INSERT INTO `transaction_flow` VALUES (2503, 2100, 6, '2023-05-02 09:39:43', NULL);
INSERT INTO `transaction_flow` VALUES (2504, 2100, 7, '2023-05-05 17:30:00', NULL);
INSERT INTO `transaction_flow` VALUES (2505, 2100, 8, '2023-05-05 17:30:03', NULL);
INSERT INTO `transaction_flow` VALUES (2506, 2101, 3, '2023-05-16 22:55:20', NULL);
INSERT INTO `transaction_flow` VALUES (2507, 2101, 5, '2023-05-17 14:50:10', NULL);
INSERT INTO `transaction_flow` VALUES (2508, 2101, 6, '2023-05-17 15:50:10', NULL);
INSERT INTO `transaction_flow` VALUES (2509, 2101, 7, '2023-05-19 10:30:50', NULL);
INSERT INTO `transaction_flow` VALUES (2510, 2101, 8, '2023-05-19 11:30:50', NULL);
INSERT INTO `transaction_flow` VALUES (2511, 2102, 3, '2023-05-21 15:11:23', NULL);
INSERT INTO `transaction_flow` VALUES (2512, 2102, 5, '2023-05-22 09:33:43', NULL);
INSERT INTO `transaction_flow` VALUES (2513, 2103, 3, '2023-05-23 13:16:23', NULL);
INSERT INTO `transaction_flow` VALUES (2514, 2103, 5, '2023-05-24 11:43:23', NULL);
INSERT INTO `transaction_flow` VALUES (2515, 2103, 6, '2023-05-24 12:39:23', NULL);
INSERT INTO `transaction_flow` VALUES (2516, 2104, 3, '2023-05-25 11:41:00', NULL);
INSERT INTO `transaction_flow` VALUES (2517, 2104, 5, '2023-05-26 14:23:43', NULL);
INSERT INTO `transaction_flow` VALUES (2518, 2104, 6, '2023-05-26 15:34:23', NULL);
INSERT INTO `transaction_flow` VALUES (2519, 2104, 7, '2023-05-28 22:10:20', NULL);
INSERT INTO `transaction_flow` VALUES (2520, 2105, 3, '2023-05-29 23:11:23', NULL);
INSERT INTO `transaction_flow` VALUES (2521, 2105, 5, '2023-05-30 11:41:03', NULL);
INSERT INTO `transaction_flow` VALUES (2522, 2105, 6, '2023-05-30 12:40:23', NULL);
INSERT INTO `transaction_flow` VALUES (2523, 2105, 10, '2023-05-31 19:09:13', NULL);
INSERT INTO `transaction_flow` VALUES (2524, 2105, 0, '2023-05-31 20:23:13', NULL);
INSERT INTO `transaction_flow` VALUES (2525, 2106, 3, '2023-06-03 15:23:33', NULL);
INSERT INTO `transaction_flow` VALUES (4001, 4001, 1, '2023-06-01 01:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4002, 4001, 2, '2023-06-01 02:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4003, 4001, 3, '2023-06-01 03:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4004, 4001, 4, '2023-06-01 10:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4005, 4001, 5, '2023-06-01 15:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4006, 4001, 6, '2023-06-01 19:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4007, 4001, 7, '2023-06-01 20:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4008, 4001, 8, '2023-06-01 23:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4009, 4002, 3, '2023-06-01 14:45:11', NULL);
INSERT INTO `transaction_flow` VALUES (4010, 4002, 10, '2023-06-01 15:30:45', NULL);
INSERT INTO `transaction_flow` VALUES (4011, 4002, 0, '2023-06-01 16:45:45', NULL);
INSERT INTO `transaction_flow` VALUES (4012, 4003, 2, '2023-06-01 11:45:14', NULL);
INSERT INTO `transaction_flow` VALUES (4013, 4004, 3, '2023-06-01 11:45:14', NULL);
INSERT INTO `transaction_flow` VALUES (4014, 4005, 3, '2023-06-01 09:14:15', NULL);
INSERT INTO `transaction_flow` VALUES (4015, 4006, 3, '2023-06-01 10:25:16', NULL);
INSERT INTO `transaction_flow` VALUES (4016, 4007, 3, '2023-06-01 00:32:13', NULL);
INSERT INTO `transaction_flow` VALUES (4017, 4007, 6, '2023-06-01 14:14:15', NULL);
INSERT INTO `transaction_flow` VALUES (4018, 4008, 1, '2023-05-31 01:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4019, 4008, 2, '2023-05-31 02:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4020, 4008, 3, '2023-05-31 03:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4021, 4008, 4, '2023-05-31 10:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4022, 4008, 5, '2023-05-31 15:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4023, 4008, 6, '2023-05-31 19:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4024, 4008, 7, '2023-05-31 20:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4025, 4008, 8, '2023-05-31 23:00:45', NULL);
INSERT INTO `transaction_flow` VALUES (4026, 4009, 3, '2023-05-31 05:00:45', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                         `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                         `balance` decimal(10, 2) NOT NULL DEFAULT 0.00,
                         `vip_value` int NOT NULL DEFAULT 0,
                         `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `verification_status` int NOT NULL DEFAULT 0,
                         `payment_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                         PRIMARY KEY (`id`) USING BTREE,
                         CONSTRAINT `user_chk_1` CHECK (`type` in (_utf8mb4'B',_utf8mb4'S')),
                         CONSTRAINT `user_chk_2` CHECK (`verification_status` in (0,1,2,3))
) ENGINE = InnoDB AUTO_INCREMENT = 90003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1001, 'zhangsan', 'eiOXB8JliHr6ZGIOAaQ9CA==', 'B', 'test1', 'phone1', 'email1@qq.com', 64.00, 0, '张三', '330181200304053333', 3, '123456');
INSERT INTO `user` VALUES (1002, 'lisi', 'i9XNSnbaZ6T077OGgmCF0Q==', 'B', 'test2', 'phone2', 'email2@qq.com', 40.00, 0, '李四', '330181200304053334', 3, '123456');
INSERT INTO `user` VALUES (1003, 'wangwu', '4VDQpqYiuLFd/AwMsq5naA==', 'S', 'test3', '', '', 0.00, 0, '王五', '330181200304053335', 1, '123456');
INSERT INTO `user` VALUES (1004, 'zhaoliu', 'HqLe4cM3IWJsaO3kwY5E1Q==', 'B', 'test4', '', 'email4@qq.com', 0.00, 0, '赵六', '330181200304053337', 2, '123456');
INSERT INTO `user` VALUES (2001, 'zhangsanyi', 'i03DCLan82rHotqZp0KU3Q==', 'B', '买家张三一', '18027775580', 'zhangsan@qq,com', 10000.00, 90, '张三一', '1111', 3, '123456');
INSERT INTO `user` VALUES (2002, 'xiaolu', 'qbJJORVwS8zo9rCt92x/Yw==', 'B', '没有身份验证的小卢', '18914325570', 'xiaolu@126.com', 100.00, 10, '卢某某', '1112', 0, '123456');
INSERT INTO `user` VALUES (2003, 'xiaobai', 'or3WcVAwKlyt RVwZ8Wbyw==', 'B', '刚注册身份的小白', '18947555843', 'xiaobai@126.com', 0.00, 0, '白某某', '1115', 3, '123456');
INSERT INTO `user` VALUES (2004, 'lisier', 'dqErEXA7WAFqzUkoBLOiKg==', 'S', '诚信卖家李四二', '18934236658', 'lisi1227@qq.com', 2000.00, 100, '李四二', '1113', 3, '123456');
INSERT INTO `user` VALUES (2005, 'wangwusan', 'nTANIMac UkwPzgHOWkbyw==', 'S', '黑心卖家王五三', '18054246854', 'wangwurich@qq.com', 10000.00, 1, '王五三', '1114', 3, '123456');
INSERT INTO `user` VALUES (4001, 'A', '', 'B', '123', '', '', 70.00, 0, '李四', '444', 3, '');
INSERT INTO `user` VALUES (4002, 'B', '', 'B', '', '1', '', 85.00, 0, '', '1', 1, '');
INSERT INTO `user` VALUES (4003, 'C', '', 'B', '', '1', '', 100.00, 0, '', '1', 1, '');
INSERT INTO `user` VALUES (4004, 'D', '', 'B', '', '1', '', 85.00, 0, '', '1', 1, '');
INSERT INTO `user` VALUES (4005, 'E', '', 'B', '', '1', '', 75.00, 0, '', '1', 1, '');
INSERT INTO `user` VALUES (4006, 'F', '', 'B', '', '1', '', 75.00, 0, '', '1', 1, '');
INSERT INTO `user` VALUES (60001, 'xiaosi', 'vQPNRmkl2LE9AArcIepSmQ==', 'S', '卖家李四', '18027739529', 'whatever@qq.com', 100000.00, 100, '李四', '10000101001010', 3, 'lisi');
INSERT INTO `user` VALUES (90001, 'mjjd', 'mjjd', 'S', '卖家酒店', '13827730192', 'whaterveranyway@gmail.com', 144013.00, 100, '卖家酒店', '10000101001010', 3, 'mjjd');
INSERT INTO `user` VALUES (90002, 'mjhb', 'mjhb', 'S', '卖家航班', '81293992018', 'qwerth1234@126.com', 23637.00, 100, '卖家航班', '10000101001010', 3, 'mjhb');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
                               `order_id` int NOT NULL AUTO_INCREMENT,
                               `buyer_id` int NULL DEFAULT NULL,
                               `seller_id` int NULL DEFAULT NULL,
                               `total_price` decimal(8, 2) NULL DEFAULT NULL,
                               `item_id` int NULL DEFAULT NULL,
                               `order_state` int NULL DEFAULT NULL,
                               `complaint_record` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
                               `item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
                               `seller_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
                               `departure_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
                               PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4009 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (1001, 1002, 90002, 6913.00, 8, 3, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (1002, 1002, 90001, 245.0, 4, 3, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (1003, 1002, 1003, 99.00, 1002, 8, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (1004, 1002, 1003, 1999.00, 1005, 5, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (2100, 2001, 2004, 1370.00, 2025, 8, '没效果！', '', '', '');
INSERT INTO `user_order` VALUES (2101, 2001, 2004, 16.80, 2001, 8, '', '', '', '');
INSERT INTO `user_order` VALUES (2102, 2001, 2005, 139.00, 2031, 5, '', '', '', '');
INSERT INTO `user_order` VALUES (2103, 2001, 2005, 2999.00, 2019, 6, '', '', '', '');
INSERT INTO `user_order` VALUES (2104, 2001, 2004, 318.00, 2010, 7, '', '', '', '');
INSERT INTO `user_order` VALUES (2105, 2001, 2005, 1699.00, 2022, 0, '不想要了！', '', '', '');
INSERT INTO `user_order` VALUES (2106, 2001, 2005, 116.00, 2037, 3, '', '', '', '');
INSERT INTO `user_order` VALUES (4001, 4001, 54, 15.00, 14, 8, 'aaa', '', '', '');
INSERT INTO `user_order` VALUES (4002, 4001, 84, 20.00, 17, 0, 'bbb', '', '', '');
INSERT INTO `user_order` VALUES (4003, 4001, 29, 35.00, 9, 3, 'cca', '', '', '');
INSERT INTO `user_order` VALUES (4004, 4002, 29, 35.00, 9, 3, 'cca', '', '', '');
INSERT INTO `user_order` VALUES (4005, 4003, 14, 120.00, 7, 4, 'cdc', '', '', '');
INSERT INTO `user_order` VALUES (4006, 4003, 14, 60.00, 7, 5, 'cdc', '', '', '');
INSERT INTO `user_order` VALUES (4007, 4004, 20, 35.00, 7, 6, 'aat', '', '', '');
INSERT INTO `user_order` VALUES (4008, 4001, 20, 30.00, 7, 8, 'aat', '', '', '');
INSERT INTO `user_order` VALUES (4009, 4006, 20, 200000.00, 7, 3, 'aat', '', '', '');

SET FOREIGN_KEY_CHECKS = 1;
