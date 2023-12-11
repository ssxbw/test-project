-- ----------------------------
-- Records of arbitration_record
-- ----------------------------
INSERT INTO `arbitration_record` VALUES (1001, 1004, 6000000, 20230609000000, 20230615000000);

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES (1001, 'card1', '123456');
INSERT INTO `card` VALUES (1002, 'card2', '123456');

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES (1001, 'HX312', '2023-05-30 15:55:00', '2023-05-30 19:05:00', '香港国际机场T1', '首都国际机场T2', 1181.00, 'N', '香港航空', NULL);
INSERT INTO `flight` VALUES (1004, 'CZ6782', '2023-05-30 20:50:00', '2023-05-30 23:50:00', '上海浦东国际机场T2', '三亚凤凰国际机场T1', 289.00, 'N', '南方航空', NULL);

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (1000, 5.00, '水印公寓', '余杭塘路与蒋墩路交汇处绿城西溪世纪中心4号楼1楼', 'Y', 5, 191.20, NULL);
INSERT INTO `hotel` VALUES (1003, 4.60, '浙江大学圆正·启真酒店', '余杭塘路866号浙大紫金港校区内', 'N', 5, 939.00, NULL);
INSERT INTO `hotel` VALUES (1006, 4.90, '轻住·梦之舟精品LOFT酒店', '郎悦居1幢4单元217室', 'N', 3, 245.00, NULL);

-- ----------------------------
-- Records of identity
-- ----------------------------
INSERT INTO `identity` VALUES ('张三', '330181200304053333');
INSERT INTO `identity` VALUES ('李四', '330181200304053334');
INSERT INTO `identity` VALUES ('王五', '330181200304053335');
INSERT INTO `identity` VALUES ('赵六', '330181200304053337');
INSERT INTO `identity` VALUES ('孙七', '330181200304053336');

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (1002, '百草味零食', 99.00, 1003, 6, '蔓越莓夹心曲奇', '');
INSERT INTO `item` VALUES (1005, '平板电脑', 1999.00, 1003, 5, 'ipad air4', '');

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES (1001, 2, 10.00, '2023-05-26 00:00:00');
INSERT INTO `recharge` VALUES (1002, 2, 10.00, '2023-05-26 00:00:00');
INSERT INTO `recharge` VALUES (1003, 2, 20.00, '2023-05-26 00:00:00');
INSERT INTO `recharge` VALUES (1004, 1, 12.00, '2023-06-08 13:14:08');
INSERT INTO `recharge` VALUES (1005, 1, 12.00, '2023-06-08 13:16:30');
INSERT INTO `recharge` VALUES (1006, 1, 20.00, '2023-06-08 13:39:28');

-- ----------------------------
-- Records of transaction_flow
-- ----------------------------
INSERT INTO `transaction_flow` VALUES (1001, 1001, 3, '2023-05-16 14:55:20', NULL);
INSERT INTO `transaction_flow` VALUES (1002, 1003, 3, '2023-05-17 12:17:32', NULL);
INSERT INTO `transaction_flow` VALUES (1003, 1003, 8, '2023-06-07 15:59:28', NULL);
INSERT INTO `transaction_flow` VALUES (1004, 1002, 3, '2023-06-10 15:59:28', NULL);
INSERT INTO `transaction_flow` VALUES (1005, 1004, 5, '2023-06-11 10:09:28', NULL);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1001, 'zhangsan', 'eiOXB8JliHr6ZGIOAaQ9CA==', 'B', 'test1', 'phone1', 'email1@qq.com', 64.00, 0, '张三', '330181200304053333', 3, '123456');
INSERT INTO `user` VALUES (1002, 'lisi', 'i9XNSnbaZ6T077OGgmCF0Q==', 'B', 'test2', 'phone2', '', 40.00, 0, '李四', '330181200304053334', 2, '123456');
INSERT INTO `user` VALUES (1003, 'wangwu', '4VDQpqYiuLFd/AwMsq5naA==', 'S', 'test3', '', '', 0.00, 0, '王五', '330181200304053335', 1, '123456');
INSERT INTO `user` VALUES (1004, 'zhaoliu', 'HqLe4cM3IWJsaO3kwY5E1Q==', 'B', 'test4', '', 'email4@qq.com', 0.00, 0, '赵六', '330181200304053337', 2, '123456');

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (1001, 1002, 90002, 6913.00, 8, 3, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (1002, 1002, 90001, 245.0, 4, 3, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (1003, 1002, 1003, 99.00, 1002, 8, '', NULL, NULL, NULL);
INSERT INTO `user_order` VALUES (1004, 1002, 1003, 1999.00, 1005, 5, '', NULL, NULL, NULL);
