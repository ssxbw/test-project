package com.payment.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.mapper.IdentityMapper;
import com.payment.pojo.Identity;
import com.payment.pojo.User;
import com.payment.service.IdentityService;
import org.springframework.stereotype.Service;

@Service
public class IdentityServiceImpl extends ServiceImpl<IdentityMapper, Identity> implements IdentityService {
    public ApiResult<String> check(User user) {
        LambdaQueryWrapper<Identity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Identity::getIdNumber, user.getIdNumber());
        if (getOne(lqw) == null) {
            return ApiResult.error("身份信息不存在");
        }
        if (!getOne(lqw).getRealName().equals(user.getRealName())) {
            return ApiResult.error("身份信息不匹配");
        }
        return ApiResult.success("成功");
    }
}
