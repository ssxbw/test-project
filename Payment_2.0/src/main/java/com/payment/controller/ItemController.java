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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private TransactionFlowService transactionFlowService;
    @Autowired
    private CommonAccountService commonAccountService;
    @Autowired
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @GetMapping()
    public Result<List<Item>> getAllItems() {
        try {
            if (BaseContext.getCurrentId() == -1) {
                throw new CustomException("查询失败！");
            }
            LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Item::getSellerId, BaseContext.getCurrentId());
            List<Item> list = itemService.list(wrapper);
            if (list.size() == 0) { //用户是买家
                List<Item> items = itemService.list();
                if (items.size() == 0) {
                    return Result.error("现在没有任何商品");
                }
                return Result.success(items, BaseContext.getCurrentType());
            }
            return Result.success(list, BaseContext.getCurrentType()); //用户是卖家
        } catch (Exception e) {
            throw new CustomException("查询失败！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/seller")
    public Result<List<Item>> getSellerItems() {
        try {
            if (BaseContext.getCurrentId() == -1) {
                throw new CustomException("查询失败！");
            }
            LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Item::getSellerId, BaseContext.getCurrentId());
            List<Item> list = itemService.list(wrapper);
            if (list.size() == 0) { //用户是买家
                return Result.success(null, BaseContext.getCurrentType());
            }
            return Result.success(list, BaseContext.getCurrentType()); //用户是卖家
        } catch (Exception e) {
            throw new CustomException("查询失败！");
        }
    }

    @PostMapping("/seller") //卖家新增商品
    public Result<String> saveItem(@RequestBody Item item) {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("查询失败！");
        }
        //item.setSellerId(BaseContext.getCurrentId());
        List<Item> list = itemService.list();
        Integer itemId = list.get(list.size() - 1).getItemId();
        if (itemId % 3 == 0) {
            itemId += 3;
        } else {
            itemId += 3 - itemId % 3;
        }
        item.setSellerId(BaseContext.getCurrentId());
        item.setItemId(itemId);
        itemService.save(item);
        return Result.success("添加成功", BaseContext.getCurrentType());
    }

    @DeleteMapping("/seller/{itemId}") //卖家删除商品
    public Result<String> removeItem(@PathVariable Integer itemId) {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        LambdaQueryWrapper<Item> itemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        itemLambdaQueryWrapper.eq(Item::getItemId, itemId);
        itemService.remove(itemLambdaQueryWrapper);
        return Result.success("删除成功", BaseContext.getCurrentType());
    }

    @PutMapping("/seller") //卖家修改商品
    public Result<String> updateItem(@RequestBody Item item) {
        if (BaseContext.getCurrentId() == -1) {
            throw new CustomException("失败！");
        }
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Item::getItemId, item.getItemId());
        itemService.update(item, wrapper);
        if (item.getPrice().compareTo(new BigDecimal(0)) <= 0 || item.getCount() <= 0) {
            return Result.error("非法输入");
        }
        return Result.success("修改成功", BaseContext.getCurrentType());
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/{userPassword}") //付款,生成新订单、新交易流程
    public Result<String> createOrder(@PathVariable String userPassword, @RequestBody Item item) {
        try {
            LambdaQueryWrapper<Item> itemLambdaQueryWrapper = new LambdaQueryWrapper<>();
            itemLambdaQueryWrapper.eq(Item::getItemId, item.getItemId());
            item = itemService.getOne(itemLambdaQueryWrapper);
            if (BaseContext.getCurrentId() == -1) {
                throw new CustomException("失败！");
            }
            int buyer_id = BaseContext.getCurrentId();
            User user = userService.getById(buyer_id);
            if (item.getCount() <= 0) {
                return Result.error("商品库存不足！");
            }
            String s = DigestUtils.md5DigestAsHex(user.getPaymentPassword().getBytes());

            if (!userPassword.equals(s)) {
                return Result.error("付款密码错误！");
            }
            if (user.getBalance().compareTo(item.getPrice().doubleValue()) < 0) {
                return Result.error("用户余额不足");
            }
            if (item.getPrice().compareTo(new BigDecimal(1000)) > 0 && user.getVerificationStatus() == 0) {
                return Result.error("未经过身份验证的用户无法进行大额交易！");
            }
            UserOrder userOrder = new UserOrder(buyer_id, item.getSellerId(), item.getPrice(), item.getItemId(), OrderState.PAYMENT);
            userOrderService.save(userOrder);
            LambdaQueryWrapper<UserOrder> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserOrder::getBuyerId, buyer_id).eq(UserOrder::getItemId, item.getItemId()).eq(UserOrder::getOrderState, OrderState.PAYMENT);
            List<UserOrder> list = userOrderService.list(wrapper);
            Item finalItem = item;
            list.forEach((temp) -> {
                TransactionFlow transactionFlow = new TransactionFlow(temp.getOrderId(), temp.getOrderState(), LocalDateTime.now());
                transactionFlowService.save(transactionFlow);//生成交易流程
                CommonAccount commonAccount = new CommonAccount(temp.getOrderId(), temp.getTotalPrice());
                commonAccountService.save(commonAccount);
                // 用户余额减
                user.setBalance(user.getBalance() - (finalItem.getPrice().doubleValue()));
                userService.updateById(user);
            });
            item.setCount(item.getCount() - 1);
            itemService.update(item, itemLambdaQueryWrapper);
            return Result.success("付款成功", BaseContext.getCurrentType());
        } catch (Exception e) {
            throw new CustomException("付款失败！");
        }
    }
}
