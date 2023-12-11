package com.payment.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.payment.pojo.Manager;
import com.payment.vo.ManagerInput;
import com.payment.vo.ModifyKeyInput;
import com.payment.vo.RegisterInput;
import com.payment.vo.ResultM;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

//接口
public interface IManagerService extends IService<Manager> {
    ResultM Verify(ManagerInput managerInput);
    ResultM addManager(RegisterInput registerInput);
    ResultM ModifyKey(ServletRequest servletRequest, ServletResponse servletResponse, ModifyKeyInput input);
}
