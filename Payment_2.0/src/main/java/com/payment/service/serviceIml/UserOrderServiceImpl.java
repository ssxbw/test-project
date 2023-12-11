package com.payment.service.serviceIml;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.mapper.ItemMapper;
import com.payment.mapper.UserMapper;
import com.payment.mapper.UserOrderMapper;
import com.payment.pojo.*;
import com.payment.service.UserOrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.ognl.security.UserMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserOrderDto> getAllById(Integer orderId) {
        List<UserOrder> list = userOrderMapper.getAllById(orderId);
        list.removeIf(userOrder -> userOrder.getItemId() % 3 != 0);
        List<UserOrderDto> userOrderDtoList = list.stream().map((item -> {
            List<TransactionFlow> flows = userOrderMapper.getFlowsByOrderId(item.getOrderId());
            UserOrderDto userOrderDto = new UserOrderDto();
            BeanUtils.copyProperties(item, userOrderDto);
            LambdaQueryWrapper<Item> itemWrapper = new LambdaQueryWrapper<>();
            itemWrapper.eq(Item::getItemId, item.getItemId());
            Item one = itemMapper.selectOne(itemWrapper);
            for (TransactionFlow flow : flows) {
                flow.setOrderStateString(OrderState.orderState.get(flow.getOrderState()));
            }
            userOrderDto.setTransactionFlowList(flows);
            userOrderDto.setItemDescription(one.getItemDescription());
            User seller = userMapper.selectById(item.getSellerId());
            User buyer = userMapper.selectById(item.getBuyerId());
            userOrderDto.setSeller_name(seller.getNickname());
            userOrderDto.setBuyer_name(buyer.getNickname());
            userOrderDto.setOrderStateString(OrderState.orderState.get(userOrderDto.getOrderState()));
            return userOrderDto;
        })).toList();
        return userOrderDtoList;
    }

    public ApiResult<String> check(List<Order> orders, HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("user");
        LambdaQueryWrapper<UserOrder> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserOrder::getBuyerId, id);
        List<UserOrder> userOrders = list(lqw);
        if (userOrders.size() == 0) return ApiResult.error("用户无订单");
        for (UserOrder userOrder : userOrders) {
            Order order = new Order();
            order.setOrderId(userOrder.getOrderId());
            order.setSellerId(userOrder.getSellerId());
            order.setItemId(userOrder.getItemId());
            order.setPrice(userOrder.getTotalPrice().doubleValue());
            order.setState(userOrder.getOrderState());
            orders.add(order);
        }
        return ApiResult.success("成功");
    }

}
