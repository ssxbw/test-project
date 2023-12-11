package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("card")
public class Card {
    private int id;
    private String number;
    private String password;
}
