INSERT INTO `user` VALUES (2001, 'zhangsanyi', 'i03DCLan82rHotqZp0KU3Q==', 'B', '买家张三一', '18027775580','zhangsan@qq,com', 10000.00, 90, '张三一', '1111', 3, '123456');
INSERT INTO `user` VALUES (2002, 'xiaolu', 'qbJJORVwS8zo9rCt92x/Yw==', 'B', '没有身份验证的小卢', '18914325570', 'xiaolu@126.com', 100.00, 10, '卢某某', '1112', 0, '123456');
INSERT INTO `user` VALUES (2003, 'xiaobai', 'or3WcVAwKlyt RVwZ8Wbyw==', 'B', '刚注册身份的小白', '18947555843', 'xiaobai@126.com', 0.00, 0, '白某某', '1115', 3, '123456');
INSERT INTO `user` VALUES (2004, 'lisier', 'dqErEXA7WAFqzUkoBLOiKg==', 'S', '诚信卖家李四二', '18934236658', 'lisi1227@qq.com', 2000.00, 100, '李四二', '1113', 3, '123456');
INSERT INTO `user` VALUES (2005, 'wangwusan', 'nTANIMac UkwPzgHOWkbyw==', 'S', '黑心卖家王五三', '18054246854', 'wangwurich@qq.com', 10000.00, 1, '王五三', '1114', 3, '123456');

INSERT INTO `item` VALUES (2001, '牙膏', 16.80, 2004, 1000, '云南白药牙膏', '/common/download?name=tb_image_share_1686469092655.jpg');
INSERT INTO `item` VALUES (2002, '零食', 89.00, 2004, 80, '三只松鼠零食大礼包', '/common/download?name=tb_image_share_1686469127194.jpg');
INSERT INTO `item` VALUES (2003, '笔记本电脑', 8999.00, 2004, 50, '拯救者R9000P', '/common/download?name=tb_image_share_1686469147587.jpg');
INSERT INTO `item` VALUES (2004, '(售罄)懒人沙发', 318.00, 2004, 0, 'lucksac懒人沙发豆', '/common/download?name=tb_image_share_1686468191936.jpg');
INSERT INTO `item` VALUES (2005, '外星人电脑', 19999.99, 2005, 50, '外星人m16', '/common/download?name=tb_image_share_1686469533703.jpg');
INSERT INTO `item` VALUES (2006, '机械键盘', 699.00, 2005, 100, 'ROG游侠RX', '/common/download?name=tb_image_share_1686469752710.jpg');
INSERT INTO `item` VALUES (2007, '限量版球鞋', 2999.00, 2005, 10, 'AJ11康扣', '/common/download?name=tb_image_share_1686470291516.jpg');
INSERT INTO `item` VALUES (2008, '头戴式耳机', 1699.00, 2005, 30, 'BoseQC45耳机', '/common/download?name=tb_image_share_1686470460824.jpg');
INSERT INTO `item` VALUES (2009, '精华水', 1370.00, 2004, 20, 'LAMER海蓝之谜精粹水', '/common/download?name=tb_image_share_1686471397562.jpg');
INSERT INTO `item` VALUES (2010, '文创', 139.00, 2004, 1000, '故宫金桂浮月杯子', '/common/download?name=tb_image_share_1686471661564.jpg');
INSERT INTO `item` VALUES (2011, '食物', 139.00, 2005, 10000, '麦当劳欢聚餐', '/common/download?name=tb_image_share_1686472645910.jpg');
INSERT INTO `item` VALUES (2012, '原神周边', 149.00, 2005, 1500, '太郎丸毛绒公仔', '/common/download?name=tb_image_share_1686472791795.jpg');
INSERT INTO `item` VALUES (2013, '明日方舟周边', 58.00, 2005, 2500, '朝陇山兔毛绒玩具', '/common/download?name=tb_image_share_1686472935147.jpg');

INSERT INTO `user_order` VALUES (2100, 2001, 2004, 1370.00, 2009, 8, '没效果！', '', '', '');
INSERT INTO `user_order` VALUES (2101, 2001, 2004, 16.80, 2001, 8, '', '', '', '');
INSERT INTO `user_order` VALUES (2102, 2001, 2005, 139.00, 2011, 5, '', '', '', '');
INSERT INTO `user_order` VALUES (2103, 2001, 2005, 2999.00, 2007, 6, '', '', '', '');
INSERT INTO `user_order` VALUES (2104, 2001, 2004, 318.00, 2004, 7, '', '', '', '');
INSERT INTO `user_order` VALUES (2105, 2001, 2005, 1699.00, 2008, 0, '不想要了！', '', '', '');
INSERT INTO `user_order` VALUES (2106, 2001, 2005, 116.00, 2013, 3, '', '', '', '');

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

INSERT INTO `common_account` VALUES (2102, 139.00);
INSERT INTO `common_account` VALUES (2103, 2999.00);
INSERT INTO `common_account` VALUES (2104, 318.00);
INSERT INTO `common_account` VALUES (2106, 116.00);