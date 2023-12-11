package com.payment.controller;

import com.payment.common.ApiResult;
import com.payment.common.BaseContext;
import com.payment.common.Order;
import com.payment.common.Result;
import com.payment.pojo.*;
import com.payment.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private TransactionFlowService transactionFlowService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ArbitrationService arbitrationService;
    @Autowired
    private IHotelService hotelService;
    @Autowired
    private IFlightService flightService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IFlightClassService flightClassService;

    // 查询用户类型，付款交易组需要这个方法，请别删除
    @GetMapping("/type")
    public Result<String> type(){
        return Result.success(BaseContext.getCurrentType(),BaseContext.getCurrentType());
    }

    //查询用户信息
    @GetMapping("/info")
    public ApiResult<User> info(HttpServletRequest request) {
        return userService.info(request);
    }

    //登入
    @GetMapping("/login")
    public ApiResult<User>login(HttpServletRequest request, User user) {
        ApiResult<User> res = userService.login(request, user);
        if (res.getCode() == 0) {
            return ApiResult.error(res.getMsg());
        }
        if (arbitrationService.CheckArbitration(res.getData().getId()).getCode() == 2) {
            return ApiResult.error("黑名单用户");
        }

        return ApiResult.success(res.getData());
    }

    //查询用户名是否可用
    @GetMapping("/check/username")
    public ApiResult<String> checkUsername(User user) {
        return userService.checkUsername(user);
    }

    //注册
    @GetMapping("/register")
    public ApiResult<String> register(User user) {
        if (identityService.check(user).getCode() == 0) {
            return identityService.check(user);
        }
        if (userService.register(user).getCode() == 0) {
            return userService.register(user);
        }
        return ApiResult.success("注册成功");
    }

    //登出
    @GetMapping("/logout")
    public ApiResult<String> logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    //注销
    @GetMapping("/logoff")
    public ApiResult<String> logoff(HttpServletRequest request) {
        return userService.logoff(request);
    }

    //找回密码
    @GetMapping("/retrieve/password")
    public ApiResult<String> retrieve_password(User user) {
        return userService.retrieve_password(user);
    }

    //修改密码
    @GetMapping("/modify/password")
    public ApiResult<String> modify_password(HttpServletRequest request, String oldPassword, String newPassword) {
        return userService.modify_password(request, oldPassword, newPassword);
    }

    //修改支付密码
    @GetMapping("/modify/paymentPassword")
    public ApiResult<String> modify_paymentPassword(HttpServletRequest request, String oldPaymentPassword, String newPaymentPassword) {
        return userService.modify_paymentPassword(request, oldPaymentPassword, newPaymentPassword);
    }

    //修改昵称
    @GetMapping("/modify/nickname")
    public ApiResult<String> modify_nickname(HttpServletRequest request, String nickname) {
        return userService.modify_nickname(request, nickname);
    }

    //修改邮箱
    @GetMapping("/modify/email")
    public ApiResult<String> modify_email(HttpServletRequest request, String email) {
        return userService.modify_email(request, email);
    }

    //修改手机号
    @GetMapping("/modify/phone")
    public ApiResult<String> modify_phone(HttpServletRequest request, String phone) {
        return userService.modify_phone(request, phone);
    }

    //充值余额
    @GetMapping("/recharge")
    public ApiResult<String> recharge(HttpServletRequest request, Card card, Double delta) {
        if (cardService.check(card).getCode() == 0) {
            return cardService.check(card);
        }
        if (userService.recharge(request, delta).getCode() == 0) {
            return userService.recharge(request, delta);
        }
        if (rechargeService.recharge_record(request, delta).getCode() == 0) {
            return rechargeService.recharge_record(request, delta);
        }
        return ApiResult.success("充值成功");
    }

    //查询订单记录
    @GetMapping("/order")
    public ApiResult<List<Order>> order(HttpServletRequest request, int month, int year) {
        if (year == 0 && month != 0) {
            return ApiResult.error("输入错误");
        }
        List<Order> orders = new ArrayList<>();
        if (userOrderService.check(orders, request).getCode() == 0) {
            return ApiResult.error(userOrderService.check(orders, request).getMsg());
        }
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (transactionFlowService.check(order).getCode() == 0) {
                return ApiResult.error(transactionFlowService.check(order).getMsg());
            }
            if (order.getItemId() % 3 == 0) {
                if (itemService.check(order).getCode() == 0) {
                    return ApiResult.error(itemService.check(order).getMsg());
                }
                if (userService.check(order).getCode() == 0) {
                    return ApiResult.error(userService.check(order).getMsg());
                }
            }
            else if (order.getItemId() % 3 == 1) {
                Room room = roomService.selectRoomByRoomId(order.getItemId());
                Hotel hotel = hotelService.selectHotelByHotelId((long) room.getHotelId());
                if (hotel != null) {
                    order.setItem(hotel.getName());
                }
            }
            else {
                FlightClass flightClass = flightClassService.selectFlightClassByClassId((long) order.getItemId());
                Flight flight = flightService.selectFlightByFlightId(flightClass.getFlightId());
                if (flight != null) {
                    order.setItem(flight.getFlightNumber());
                }
            }
            if (year != 0 && month == 0) {
                if (order.getTime().getYear() + 1900 != year) {
                    orders.remove(order);
                    i--;
                }
            }
            else if (year != 0 && month != 0) {
                if (order.getTime().getYear() + 1900 != year || order.getTime().getMonth() + 1 != month) {
                    orders.remove(order);
                    i--;
                }
            }
        }
        for (int i = 0; i < orders.size(); i++) {
            int k = i;
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(j).getTime().after(orders.get(k).getTime())) {
                    k = j;
                }
            }
            if (i != k) {
                Order order = orders.get(k);
                orders.set(k, orders.get(i));
                orders.set(i, order);
            }
        }
        return ApiResult.success(orders);
    }

    //订单记录统计
    @GetMapping("/order/statistics")
    public ApiResult<List<Double>> order_statistics(HttpServletRequest request, int year) {
        List<Double> amounts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            amounts.add(0.00);
        }
        List<Order> orders = new ArrayList<>();
        if (userOrderService.check(orders, request).getCode() == 0) {
            return ApiResult.error(userOrderService.check(orders, request).getMsg());
        }
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (transactionFlowService.check_final(order).getCode() != 0) {
                if (year == order.getTime().getYear() + 1900) {
                    Double tm = amounts.get(order.getTime().getMonth());
                    amounts.set(order.getTime().getMonth(), tm + order.getPrice());
                }
            }
        }
        return ApiResult.success(amounts);
    }

}