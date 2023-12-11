package com.payment.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.common.BaseContext;
import com.payment.common.Order;
import com.payment.mapper.UserMapper;
import com.payment.pojo.User;
import com.payment.service.UserService;
import com.payment.vo.ResultM;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    public ApiResult<User> info(HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("user");
        return ApiResult.success(getById(id));
    }

    public ApiResult<User> login(HttpServletRequest request, User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        User emp = getOne(lqw);
        if (emp == null) {
            return ApiResult.error("用户名不存在");
        }
        if (!emp.getPassword().equals(user.getPassword())) {
            return ApiResult.error("密码错误");
        }
        request.getSession().setAttribute("user", emp.getId());
        BaseContext.setCurrentId(emp.getId());
        switch (emp.getType()){
            case 'B'->BaseContext.setCurrentType("0");
            case 'S'->BaseContext.setCurrentType("1");
        }
        return ApiResult.success(emp);
    }

    public ApiResult<String> checkUsername(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        User emp = getOne(lqw);
        if (emp != null) {
            return ApiResult.error("用户名已存在");
        }
        return ApiResult.success("用户名可用");
    }

    public ApiResult<String> register(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getIdNumber, user.getIdNumber());
        if (getOne(lqw) != null) {
            return ApiResult.error("身份信息已被注册");
        }
        lqw.eq(User::getUsername, user.getUsername());
        if (getOne(lqw) != null) {
            return ApiResult.error("用户名已存在");
        }
        int status = 1;
        if (user.getEmail() != null) status++;
        if (user.getPhone() != null) status++;
        user.setVerificationStatus(status);
        save(user);
        return ApiResult.success("注册成功");
    }

    public ApiResult<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        BaseContext.setCurrentId(-1);
        BaseContext.setCurrentType("");
        return ApiResult.success("登出成功");
    }

    public ApiResult<String> logoff(HttpServletRequest request) {
        int id = (int) request.getSession().getAttribute("user");
        if (!removeById(id)) {
            return ApiResult.error("注销失败");
        }
        request.getSession().removeAttribute("user");
        return ApiResult.success("注销成功");
    }

    public ApiResult<String> retrieve_password(User user) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername, user.getUsername());
        User emp = getOne(lqw);
        if (emp == null) {
            return ApiResult.error("用户名不存在");
        }
        if (!emp.getRealName().equals(user.getRealName()) || !emp.getIdNumber().equals(user.getIdNumber())) {
            return ApiResult.error("身份信息错误");
        }
        if (!emp.getNickname().equals(user.getNickname())) {
            return ApiResult.error("密保问题错误");
        }
        return ApiResult.success(emp.getPassword());
    }

    public ApiResult<String> modify_password(HttpServletRequest request, String oldPassword, String newPassword) {
        int id = (int) request.getSession().getAttribute("user");
        if (!getById(id).getPassword().equals(oldPassword)) {
            return ApiResult.error("原密码错误");
        }
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.eq(User::getId, id);
        luw.set(User::getPassword, newPassword);
        update(luw);
        return ApiResult.success("修改成功");
    }

    public ApiResult<String> modify_paymentPassword(HttpServletRequest request, String oldPaymentPassword, String newPaymentPassword) {
        int id = (int) request.getSession().getAttribute("user");
        if (!getById(id).getPaymentPassword().equals(oldPaymentPassword)) {
            return ApiResult.error("原密码错误");
        }
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.eq(User::getId, id);
        luw.set(User::getPaymentPassword, newPaymentPassword);
        update(luw);
        return ApiResult.success("修改成功");
    }

    public ApiResult<String> modify_nickname(HttpServletRequest request, String nickname) {
        int id = (int) request.getSession().getAttribute("user");
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.eq(User::getId, id);
        luw.set(User::getNickname, nickname);
        update(luw);
        return ApiResult.success("修改成功");
    }

    public ApiResult<String> modify_email(HttpServletRequest request, String email) {
        int id = (int) request.getSession().getAttribute("user");
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.eq(User::getId, id);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, id);
        User emp = getOne(lqw);
        if (emp.getEmail().equals("") && !email.equals("")) {
            int status = emp.getVerificationStatus();
            if (status < 3) luw.set(User::getVerificationStatus, status + 1);
            update(luw);
        }
        else if (!emp.getEmail().equals("") && email.equals("")) {
            int status = emp.getVerificationStatus();
            if (status >= 2) luw.set(User::getVerificationStatus, status - 1);
            update(luw);
        }
        luw.set(User::getEmail, email);
        update(luw);
        return ApiResult.success("修改成功");
    }

    public ApiResult<String> modify_phone(HttpServletRequest request, String phone) {
        int id = (int) request.getSession().getAttribute("user");
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.eq(User::getId, id);
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, id);
        User emp = getOne(lqw);
        if (emp.getPhone().equals("") && !phone.equals("")) {
            int status = emp.getVerificationStatus();
            if (status < 3) luw.set(User::getVerificationStatus, status + 1);
            update(luw);
        }
        else if (!emp.getPhone().equals("") && phone.equals("")) {
            int status = emp.getVerificationStatus();
            if (status >= 2) luw.set(User::getVerificationStatus, status - 1);
            update(luw);
        }
        luw.set(User::getPhone, phone);
        update(luw);
        return ApiResult.success("修改成功");
    }

    public ApiResult<String> recharge(HttpServletRequest request, Double delta) {
        int id = (int) request.getSession().getAttribute("user");
        LambdaUpdateWrapper<User> luw = new LambdaUpdateWrapper<>();
        luw.eq(User::getId, id);
        luw.set(User::getBalance, getById(id).getBalance() + delta);
        update(luw);
        return ApiResult.success("充值成功");
    }

    public ApiResult<String> check(Order order) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getId, order.getSellerId());
        User user = getOne(lqw);
        if (user == null) return ApiResult.error("卖家不存在");
        order.setSeller(user.getNickname());
        return ApiResult.success("成功");
    }

    @Override
    public ResultM GetUserList(int PageNum, int PageSize) {
        return ResultM.success();
    }
}
