package com.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payment.pojo.Identity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IdentityMapper extends BaseMapper<Identity> {
}