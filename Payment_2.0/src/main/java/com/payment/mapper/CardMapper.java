package com.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.payment.pojo.Card;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper extends BaseMapper<Card> {
}
