package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private int id;
    private String username;
    private String password;
    private char type;
    private String nickname;
    public String phone;
    public String email;
    private Double balance;
    private int vipValue;
    private String realName;
    private String idNumber;
    private int verificationStatus;
    private String paymentPassword;

}