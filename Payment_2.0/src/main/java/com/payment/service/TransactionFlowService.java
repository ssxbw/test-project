package com.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.Item;
import com.payment.pojo.TransactionFlow;
import java.util.List;

public interface TransactionFlowService extends IService<TransactionFlow> {
    public ApiResult<String> check(Order order);
    public ApiResult<String> check_final(Order order);

    public List<TransactionFlow> getByOrderId(int order_id);
}
