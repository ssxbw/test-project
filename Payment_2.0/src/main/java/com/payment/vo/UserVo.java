package com.payment.vo;

import lombok.Data;

@Data
public class UserVo {
    int id;
    String username;
    char type;
    String phone;
    String email;
    int vipValue;
}
