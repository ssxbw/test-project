package com.payment.service;

import java.util.List;

import com.payment.pojo.Hotel;
import com.payment.pojo.User;
import com.payment.pojo.UserOrder;

/**
 * 用户Service接口
 *
 * @date 2023-06-02
 */
public interface IOrderService
{

    List<UserOrder> findjdListByUserId(String userId, String beginDate, String endDate);

    List<UserOrder> findjdListBySellerIdAndOrderState(int sellerId);

    void updateOrder(Integer orderId, Integer finish);

    UserOrder findByOrderId(Integer orderId);

    List<UserOrder> findhbListByUserId(String userId, String beginDate, String endDate);

    List<UserOrder> findhbListBySellerIdAndOrderState(int sellerId);


}
