package com.payment.service.serviceIml;

import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.mapper.ItemMapper;
import com.payment.mapper.TransactionFlowMapper;
import com.payment.pojo.Item;
import com.payment.pojo.TransactionFlow;
import com.payment.service.ItemService;
import com.payment.service.TransactionFlowService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;


@Service
public class TransactionFlowServiceImpl extends ServiceImpl<TransactionFlowMapper, TransactionFlow> implements TransactionFlowService {
    @Autowired
    private TransactionFlowMapper transactionflowmapper;
    public ApiResult<String> check(Order order) {
        LambdaQueryWrapper<TransactionFlow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TransactionFlow::getOrderId, order.getOrderId()).eq(TransactionFlow::getOrderState, order.getState());
        TransactionFlow tf = getOne(lqw);
        if (tf == null) return ApiResult.error("未找到状态对应时间");
        order.setTime(Date.from(tf.getCreateTime().atZone(ZoneId.systemDefault()).toInstant()));
        return ApiResult.success("成功");
    }

    public ApiResult<String> check_final(Order order) {
        LambdaQueryWrapper<TransactionFlow> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TransactionFlow::getOrderId, order.getOrderId()).eq(TransactionFlow::getOrderState, 8);
        TransactionFlow tf = getOne(lqw);
        if (tf == null) return ApiResult.error("订单未完成");
        order.setTime(Date.from(tf.getCreateTime().atZone(ZoneId.systemDefault()).toInstant()));
        return ApiResult.success("成功");
    }

    public List<TransactionFlow> getByOrderId(int order_id){
        return transactionflowmapper.getByOrderId(order_id);
    }
}
