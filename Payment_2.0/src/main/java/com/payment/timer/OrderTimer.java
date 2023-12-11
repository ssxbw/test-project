package com.payment.timer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.payment.pojo.OrderState;
import com.payment.pojo.User;
import com.payment.pojo.UserOrder;
import com.payment.service.IOrderService;
import com.payment.service.IUserService;
import com.payment.service.UserOrderService;
import com.payment.service.UserService;

@Component
@EnableScheduling
public class OrderTimer {

    @Autowired
    private UserOrderService userOrderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    /**
     * 定时器定义，设置执行时间
     */
    // @Scheduled(cron = "0 0 22 * * ?") 每天晚上定时10点执行
//     @Scheduled(cron = "*/10 * * * * ?")
    private void process() {
        System.out.println("定时器1执行");

        List<UserOrder> list = orderService.findjdListBySellerIdAndOrderState(90001);
        for (int i = 0; i < list.size(); i++) {
            UserOrder order = list.get(i);
            orderService.updateOrder(order.getOrderId(), OrderState.FINISH);
            User user = userService.getById(90001);
            user.setBalance(user.getBalance()+(order.getTotalPrice()).doubleValue());
            userService.update(user);
        }
    }

    // 航班
    //@Scheduled(cron = "*/10 * * * * ?")
    private void process1() throws ParseException {
        System.out.println("定时器2执行");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<UserOrder> list = orderService.findhbListBySellerIdAndOrderState(90002);
        for (int i = 0; i < list.size(); i++) {
            String departureTime = list.get(i).getDepartureTime().concat(" 00:00:00");
            Date start = sdf.parse(departureTime);
            Date now = new Date();
//			long cha = start.getTime() - now.getTime();
//		       double result = cha * 1.0 / (1000 * 60 * 60);
            if(now.compareTo(start)>=0){
                UserOrder order = list.get(i);
                orderService.updateOrder(order.getOrderId(), OrderState.FINISH);
                User user = userService.getById(90002);
                user.setBalance(user.getBalance()+(order.getTotalPrice()).doubleValue());
                userService.update(user);
            }
        }
    }
}
