package com.payment.service.serviceIml;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.HotelMapper;
import com.payment.mapper.IOrderMapper;
import com.payment.mapper.IUserMapper;
import com.payment.pojo.Hotel;
import com.payment.pojo.User;
import com.payment.pojo.UserOrder;
import com.payment.service.IHotelService;
import com.payment.service.IOrderService;
import com.payment.service.IUserService;

/**
 * 酒店Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-02
 */
@Service
public class IOrderServiceImpl implements IOrderService
{
    @Autowired
    private IOrderMapper orderMapper;

    @Override
    public List<UserOrder> findjdListByUserId(String userId, String beginDate, String endDate) {
        // TODO Auto-generated method stub
        return orderMapper.findjdListByUserId(userId,beginDate,endDate);
    }

    @Override
    public List<UserOrder> findjdListBySellerIdAndOrderState(int sellerId) {
        // TODO Auto-generated method stub
        return orderMapper.findjdListBySellerIdAndOrderState(sellerId);
    }

    @Override
    public void updateOrder(Integer orderId, Integer finish) {
        orderMapper.updateOrder(orderId,finish);

    }

    @Override
    public UserOrder findByOrderId(Integer orderId) {
        // TODO Auto-generated method stub
        return orderMapper.findByOrderId(orderId);
    }

    @Override
    public List<UserOrder> findhbListByUserId(String userId, String beginDate, String endDate) {
        // TODO Auto-generated method stub
        return orderMapper.findhbListByUserId(userId,beginDate,endDate);
    }

    @Override
    public List<UserOrder> findhbListBySellerIdAndOrderState(int sellerId) {
        // TODO Auto-generated method stub
        return orderMapper.findhbListBySellerIdAndOrderState(sellerId);
    }





}
