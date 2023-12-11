package com.payment.vo;

import lombok.Data;

@Data
public class UserVo2 {
    int id;
    String username;
    char type;
    String phone;
    String email;
    int vipValue;
    Boolean isBlocked;
    String endDate;
}
