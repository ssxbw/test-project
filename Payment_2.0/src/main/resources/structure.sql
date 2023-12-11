/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : web

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 11/06/2023 18:31:03
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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for common_account
-- ----------------------------
DROP TABLE IF EXISTS `common_account`;
CREATE TABLE `common_account`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `total_price` decimal(8, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
-- Table structure for identity
-- ----------------------------
DROP TABLE IF EXISTS `identity`;
CREATE TABLE `identity`  (
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `amount` decimal(10, 2) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1048 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
