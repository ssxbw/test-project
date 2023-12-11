package com.payment.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.mapper.CardMapper;
import com.payment.pojo.Card;
import com.payment.service.CardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {
    public ApiResult<String> check(Card card) {
        LambdaQueryWrapper<Card> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Card::getNumber, card.getNumber());
        Card emp = getOne(lqw);
        if (emp == null) {
            return ApiResult.error("卡号不存在");
        }
        if (!emp.getPassword().equals(card.getPassword())) {
            return ApiResult.error("密码错误");
        }
        return ApiResult.success("成功");
    }
}
