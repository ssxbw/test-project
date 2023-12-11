package com.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payment.pojo.CommonAccount;
import com.payment.pojo.Item;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommonAccountMapper extends BaseMapper<CommonAccount> {
}
