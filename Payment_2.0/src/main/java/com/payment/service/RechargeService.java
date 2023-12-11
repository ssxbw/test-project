package com.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.common.ApiResult;
import com.payment.pojo.Recharge;
import jakarta.servlet.http.HttpServletRequest;


public interface RechargeService extends IService<Recharge> {
    public ApiResult<String> recharge_record(HttpServletRequest request, Double delta);
}
