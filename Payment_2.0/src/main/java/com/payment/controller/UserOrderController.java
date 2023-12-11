package com.payment.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.payment.common.BaseContext;
import com.payment.common.CustomException;
import com.payment.common.Result;
import com.payment.pojo.*;
import com.payment.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private TransactionFlowService transactionFlowService;
    @Autowired
    private CommonAccountService commonAccountService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    // 查询用户类型，付款交易组需要这个方法，请别删除
    @GetMapping("/type")
    public Result<String> type(){
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        return Result.success(BaseContext.getCurrentType(),BaseContext.getCurrentType());
    }

    @GetMapping() //获取该用户的所有订单及每个订单关联的交易流程
    public Result<List<UserOrderDto>> getAll() {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        Integer id = BaseContext.getCurrentId();
        List<UserOrderDto> list = userOrderService.getAllById(id);
        if (list == null) {
            return Result.error("该用户没有任何订单");
        }
        return Result.success(list,BaseContext.getCurrentType());
    }

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
@GetMapping("/sort/{StartTime}/{EndTime}/{orderStateString}") //获取该用户经筛选后的所有订单及每个订单关联的交易流程
public Result<ArrayList<UserOrderDto>> getSorted(@PathVariable Long StartTime,
                                            @PathVariable Long EndTime,
                                            @PathVariable String orderStateString) {
    if (BaseContext.getCurrentId() == -1) {
        throw new CustomException("失败！");
    }
    int id = BaseContext.getCurrentId();
    List<UserOrderDto> list = userOrderService.getAllById(id);
    ArrayList<UserOrderDto> userOrderDtos = new ArrayList<>(list);
    if(!orderStateString.equals("-1"))
    {
        userOrderDtos.removeIf(userOrderDto -> userOrderDto.getOrderState().toString().equals(orderStateString));
    }
    List<TransactionFlow> flowList;
    LocalDateTime createTime;
    Iterator<UserOrderDto> iterator = userOrderDtos.iterator();
    if(StartTime!=0&&EndTime!=0){ //过滤时间
        while(iterator.hasNext()) {
            UserOrderDto userOrderDto = iterator.next();
            flowList = userOrderDto.getTransactionFlowList();
            createTime = flowList.get(flowList.size() - 1).getCreateTime();
            if(Timestamp.valueOf(createTime).getTime() < StartTime || Timestamp.valueOf(createTime).getTime() > EndTime)
                iterator.remove();
        }
    }
    return Result.success(userOrderDtos, BaseContext.getCurrentType());
}

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/refund/{orderId}") //买家申请订单退款
    public Result<String> refund(@PathVariable Integer orderId) {
        try {
            if (BaseContext.getCurrentId() == -1) {
                throw new CustomException("失败！");
            }
            LambdaQueryWrapper<UserOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserOrder::getOrderId, orderId);
            UserOrder userOrder = userOrderService.getOne(wrapper);
            User user = userService.getById(userOrder.getBuyerId());
            userOrder.setOrderState(OrderState.FAIL);
            LambdaQueryWrapper<UserOrder> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(UserOrder::getOrderId, orderId);
            userOrderService.update(userOrder, wrapper1);
            TransactionFlow transactionFlow = new TransactionFlow(userOrder.getOrderId(), OrderState.REFUND, LocalDateTime.now());
            transactionFlowService.save(transactionFlow);
            transactionFlow.setOrderState(OrderState.FAIL);
            transactionFlow.setCreateTime(LocalDateTime.now());
            transactionFlowService.save(transactionFlow);
            LambdaQueryWrapper<CommonAccount> wrapper2 = new LambdaQueryWrapper<>();
            wrapper2.eq(CommonAccount::getOrderId, orderId);
            commonAccountService.remove(wrapper2);
            // 用户余额加
            user.setBalance(user.getBalance()+ (userOrder.getTotalPrice().doubleValue()));
            userService.updateById(user);
            LambdaQueryWrapper<Item> itemLambdaQueryWrapper = new LambdaQueryWrapper<>();
            itemLambdaQueryWrapper.eq(Item::getItemId, userOrder.getItemId());
            Item item = itemService.getOne(itemLambdaQueryWrapper);
            item.setCount(item.getCount() + 1);
            itemService.update(item,itemLambdaQueryWrapper);
            return Result.success("退款成功！", BaseContext.getCurrentType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("退款失败！");
        }
    }

    @PutMapping("/complaint") //投诉处理
    public Result<String> complaint(@RequestBody UserOrder userOrder) {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        Integer orderId = userOrder.getOrderId();
        String complaintRecord = userOrder.getComplaintRecord();
        LambdaQueryWrapper<UserOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOrder::getOrderId, orderId);
        userOrder = userOrderService.getOne(wrapper);
        userOrder.setComplaintRecord(complaintRecord);
        LambdaQueryWrapper<UserOrder> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(UserOrder::getOrderId, orderId);
        userOrderService.update(userOrder, wrapper1);
        return Result.success("投诉成功", BaseContext.getCurrentType());
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/received") //确认收货
    public Result<String> receive(@RequestBody UserOrder userOrder) {
        try {
            if (BaseContext.getCurrentId() == -1) {
                throw new CustomException("失败！");
            }
            LambdaQueryWrapper<UserOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserOrder::getOrderId, userOrder.getOrderId());
            userOrder = userOrderService.getOne(wrapper);
            userOrder.setOrderState(OrderState.RECEIVED);
            userOrderService.update(userOrder, wrapper);
            TransactionFlow transactionFlow = new TransactionFlow(userOrder.getOrderId(), OrderState.RECEIVED, LocalDateTime.now());
            transactionFlowService.save(transactionFlow);
            userOrder.setOrderState(OrderState.FINISH);
            userOrderService.update(userOrder, wrapper);
            transactionFlow.setOrderState(OrderState.FINISH);
            transactionFlowService.save(transactionFlow);
            LambdaQueryWrapper<CommonAccount> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(CommonAccount::getOrderId, userOrder.getOrderId());
            commonAccountService.remove(wrapper1);
            User seller = userService.getById(userOrder.getSellerId());
            seller.setBalance(seller.getBalance()+(userOrder.getTotalPrice().doubleValue()));
            User buyer = userService.getById(userOrder.getBuyerId());
            buyer.setVipValue(buyer.getVipValue() + userOrder.getTotalPrice().intValue());
            seller.setVipValue(seller.getVipValue() + userOrder.getTotalPrice().intValue());
            userService.updateById(seller);
            userService.updateById(buyer);
            return Result.success("收货成功！", BaseContext.getCurrentType());
        } catch (Exception e) {
            throw new CustomException("收货失败");
        }
    }


    @PutMapping("/ship") //卖家确认发货
    public Result<String> ship(@RequestBody UserOrder userOrder) {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        LambdaQueryWrapper<UserOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOrder::getOrderId, userOrder.getOrderId());
        userOrder = userOrderService.getOne(wrapper);
        userOrder.setOrderState(OrderState.SHIPPED);
        userOrderService.update(userOrder, wrapper);
        TransactionFlow transactionFlow = new TransactionFlow(userOrder.getOrderId(), OrderState.SHIPPED, LocalDateTime.now());
        transactionFlowService.save(transactionFlow);
        userOrder.setOrderState(OrderState.TRANSITING);
        userOrderService.update(userOrder, wrapper);
        transactionFlow.setOrderState(OrderState.TRANSITING);
        transactionFlow.setCreateTime(LocalDateTime.now());
        transactionFlowService.save(transactionFlow);
        return Result.success("发货成功", BaseContext.getCurrentType());
    }
}
