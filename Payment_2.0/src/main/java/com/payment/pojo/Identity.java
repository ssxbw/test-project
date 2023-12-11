package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("identity")
public class Identity {
    private String realName;
    private String idNumber;
}
