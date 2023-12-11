package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("recharge")
public class Recharge {
    private int id;
    private int userId;
    private Double amount;
    private Date date;
}
