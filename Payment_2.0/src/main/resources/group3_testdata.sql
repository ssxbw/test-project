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

 Date: 11/06/2023 17:39:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `elk_file` VALUES (66, 'http://localhost:80/payment/images/7debfb70-f4eb-4665-9732-95e957038225.png', 'HG.png', '7debfb70-f4eb-4665-9732-95e957038225.png', 'HG.png', NULL, '0', '0', '17828', NULL);
INSERT INTO `elk_file` VALUES (99, 'http://localhost:80/payment/images/5af516a4-ef0d-4875-a062-aa61c92b2cc2.png', 'tz_.png', '5af516a4-ef0d-4875-a062-aa61c92b2cc2.png', 'tz_.png', NULL, '0', '0', '88406', NULL);

INSERT INTO `flight` VALUES (20000, 'HX312', '2023-06-09 15:55:00', '2023-05-30 19:05:00', '香港国际机场T1', '首都国际机场T2', '1181.0', 'N', '香港航空', '99');
INSERT INTO `flight` VALUES (20003, 'CZ6782', '2023-06-11 20:50:00', '2023-05-30 23:50:00', '上海浦东国际机场T2', '三亚凤凰国际机场T1', '289.0', 'N', '南方航空', '99');
INSERT INTO `flight` VALUES (20006, 'HO1379', '2023-06-10 08:05:00', '2023-05-30 12:10:00', '上海浦东国际机场T2', '成田国际机场T2', '1521.0', 'Y', '均瑶航空', '99');

INSERT INTO `flight_class` VALUES (8, 20000, '商务舱', 6913.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (11, 20003, '经济舱', 289.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (14, 20003, '商务舱', 450.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (17, 20006, '经济舱', 1521.00, 'Y', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (20, 20006, '商务舱', 5513.00, 'N', '99', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2007, 20006, '测试', 99.00, 'Y', '108', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2009, 20000, '测试', 100.00, 'Y', '114', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2010, 20000, 'ce', 1.00, 'Y', '115', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2011, 20000, '1', 1.00, 'Y', '116', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2014, 20000, '测试', 11.00, 'Y', '117', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
INSERT INTO `flight_class` VALUES (2017, 20000, '测试', 100.00, 'Y', '118', 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);


INSERT INTO `hotel` VALUES (10000, 1.00, '水印公寓', '余杭塘路与蒋墩路交汇处绿城西溪世纪中心4号楼1楼', 'Y', 5, 191.20, NULL);
INSERT INTO `hotel` VALUES (10003, 4.60, '浙江大学圆正·启真酒店', '余杭塘路866号浙大紫金港校区内', 'N', 5, 939.00, NULL);
INSERT INTO `hotel` VALUES (10006, 4.90, '轻住·梦之舟精品LOFT酒店', '郎悦居1幢4单元217室', 'N', 3, 245.00, NULL);
INSERT INTO `hotel` VALUES (10023, 1.00, '阳光酒店', '12', '0', 3, 100.00, '');
INSERT INTO `hotel` VALUES (10028, 2.00, '测试', '测试', '0', 3, 200.00, '99');


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

INSERT INTO `user` VALUES (60001, 'xiaosi', 'xiaosi', 'S', '卖家李四', '18027739529', 'whatever@qq.com', 100000.00, 100, '李四', '10000101001010', 3, 'lisi');
INSERT INTO `user` VALUES (90001, 'mjjd', 'mjjd', 'S', '卖家酒店', '13827730192', 'whaterveranyway@gmail.com', 144013.00, 100, '卖家酒店', '10000101001010', 3, 'mjjd');
INSERT INTO `user` VALUES (90002, 'mjhb', 'mjhb', 'S', '卖家航班', '81293992018', 'qwerth1234@126.com', 23637.00, 100, '卖家航班', '10000101001010', 3, 'mjhb');


SET FOREIGN_KEY_CHECKS = 1;
