package com.payment.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OrderState {
    public static HashMap<Integer, String> orderState = new HashMap<>(){{
        put(2, "未付款");
        put(3, "已付款");
        put(4, "待处理");
        put(5, "已发货");
        put(6, "运输中");
        put(7, "已收货");
        put(8, "订单成功");
        put(9, "取消订单");
        put(10, "退款");
        put(0, "订单失败");
    }};

    public static final Integer NONPAYMENT = 2;//未付款
    public static final Integer PAYMENT = 3;//已付款
    public static final Integer WAITING = 4;//待处理
    public static final Integer SHIPPED = 5;//已发货
    public static final Integer TRANSITING = 6;//运输中
    public static final Integer RECEIVED = 7;//已收货
    public static final Integer FINISH = 8;//订单成功
    public static final Integer CANCEL = 9;//取消订单
    public static final Integer REFUND = 10;//申请退款
    public static final Integer FAIL = 0;//订单失败
}
