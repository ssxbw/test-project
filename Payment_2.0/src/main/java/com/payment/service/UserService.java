package com.payment.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.User;
import com.payment.vo.ResultM;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    ResultM GetUserList(int PageNum, int PageSize);
    public ApiResult<User> info(HttpServletRequest request);
    public ApiResult<User> login(HttpServletRequest request, User user);
    public ApiResult<String> checkUsername(User user);
    public ApiResult<String> register(User user);
    public ApiResult<String> logout(HttpServletRequest request);
    public ApiResult<String> logoff(HttpServletRequest request);
    public ApiResult<String> retrieve_password(User user);
    public ApiResult<String> modify_password(HttpServletRequest request, String oldPassword, String newPassword);
    public ApiResult<String> modify_paymentPassword(HttpServletRequest request, String oldPaymentPassword, String newPaymentPassword);
    public ApiResult<String> modify_nickname(HttpServletRequest request, String nickname);
    public ApiResult<String> modify_email(HttpServletRequest request, String email);
    public ApiResult<String> modify_phone(HttpServletRequest request, String phone);
    public ApiResult<String> recharge(HttpServletRequest request, Double delta);
    public ApiResult<String> check(Order order);
}
