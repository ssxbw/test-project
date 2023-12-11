package com.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.UserOrder;
import com.payment.pojo.UserOrderDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserOrderService extends IService<UserOrder> {
    List<UserOrderDto> getAllById(Integer id);

    public ApiResult<String> check(List<Order> orders, HttpServletRequest request);

}
