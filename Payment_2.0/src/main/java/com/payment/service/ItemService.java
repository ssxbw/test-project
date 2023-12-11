package com.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.Item;


public interface ItemService extends IService<Item> {
    public ApiResult<String> check(Order order);
}
