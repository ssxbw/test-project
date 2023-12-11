package com.payment.service.serviceIml;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.mapper.RechargeMapper;
import com.payment.pojo.Recharge;
import com.payment.service.RechargeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RechargeServiceImpl extends ServiceImpl<RechargeMapper, Recharge> implements RechargeService {

    public ApiResult<String> recharge_record(HttpServletRequest request, Double delta) {
        Recharge recharge = new Recharge();
        recharge.setUserId((Integer) request.getSession().getAttribute("user"));
        recharge.setAmount(delta);
        recharge.setDate(new Date());
        save(recharge);
        return ApiResult.success("成功");
    }

}
