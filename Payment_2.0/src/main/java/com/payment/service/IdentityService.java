package com.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.pojo.Identity;
import com.payment.pojo.User;


public interface IdentityService extends IService<Identity> {
    public ApiResult<String> check(User user);
}
