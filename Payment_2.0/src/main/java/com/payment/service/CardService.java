package com.payment.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.pojo.Card;

public interface CardService extends IService<Card> {
    public ApiResult<String> check(Card card);
}
