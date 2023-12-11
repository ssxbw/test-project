package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("manager_info")//保持对应的表的名字(用下划线命名法）
public class Manager {
    @TableId(value = "manager_id")
    public String managerId;
    public String managerKey;
    public boolean isLocked;
    public String telephoneNum;
    public String managerType;//下划线命名法（数据库）转换为驼峰命名法（开发代码）
    public long lockStartTime;
    public long lockDuration;
    public int loginTimes;

}
