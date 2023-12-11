package com.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.payment.pojo.Hotel;
import com.payment.pojo.User;
import com.payment.pojo.UserOrder;

/**
 * 酒店Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-02
 */
@Mapper
public interface IOrderMapper
{

    List<UserOrder> findjdListByUserId(String userId, String beginDate, String endDate);

    List<UserOrder> findjdListBySellerIdAndOrderState(int sellerId);

    void updateOrder(Integer orderId, Integer finish);

    UserOrder findByOrderId(Integer orderId);

    List<UserOrder> findhbListByUserId(String userId, String beginDate, String endDate);

    List<UserOrder> findhbListBySellerIdAndOrderState(int sellerId);

}
