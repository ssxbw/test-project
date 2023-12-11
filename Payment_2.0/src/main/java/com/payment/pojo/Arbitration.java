package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "arbitration_record")
public class Arbitration {
    @TableId(value = "arbitration_id")
    int arbitrationId;
    int arbitratedUserId;
    long arbitrationTime;
    long startTime;
    long endTime;
}
