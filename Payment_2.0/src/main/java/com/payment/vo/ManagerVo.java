package com.payment.vo;

import lombok.Data;

@Data //自动实现Getdata 和 setData的功能//log.info时打印出类的具体信息
public class ManagerVo {
    public String managerType;//下划线命名法（数据库）转换为驼峰命名法（开发代码）
    public String token;
    public String managerId;
    public int userId;
}
