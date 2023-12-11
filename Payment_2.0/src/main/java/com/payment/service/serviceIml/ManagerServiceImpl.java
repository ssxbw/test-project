package com.payment.service.serviceIml;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.JwtUtil;
import com.payment.common.SpringUtil;
import com.payment.mapper.ManagerMapper;
import com.payment.mapper.UserMapper;
import com.payment.pojo.Manager;
import com.payment.pojo.User;
import com.payment.service.IManagerService;
import com.payment.vo.*;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j//lombok
@Transactional
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
    @Autowired
    ManagerMapper managerMapper;
    @Autowired
    UserMapper userMapper;

    private final Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA);
    @Override
    public ResultM Verify(ManagerInput managerInput) {
        String token=null;
        Manager manager=managerMapper.selectOne(new QueryWrapper<Manager>().eq("manager_id",managerInput.getName()));
        log.info("manager={}",manager);
        if(manager==null){
            return ResultM.fail("non-exist manager",null);
        }
        if(manager.isLocked==true){//被锁时
            if(System.currentTimeMillis()-manager.lockStartTime>=manager.lockDuration){
                manager.isLocked=false;
                manager.loginTimes=0;
                manager.lockStartTime=0;
                manager.lockDuration=0;
                managerMapper.updateById(manager);
            }else {
                return ResultM.fail("too many try, wait a few minutes", null);
            }
        }
        if (!DigestUtil.bcryptCheck(managerInput.getKey(), manager.getManagerKey())) {//比较被加密的password,密码不正确时
            manager.loginTimes++;
            if(manager.loginTimes>=7){
                manager.setLockStartTime(System.currentTimeMillis());//时间戳，以秒为单位
                manager.setLockDuration(1*60*1000);
                manager.setLocked(true);
            }
            managerMapper.updateById(manager);
            return ResultM.fail("Incorrect key",null);
        }
        JwtUtil jwtUtil=new JwtUtil();
        token=jwtUtil.createToken(manager.managerId,manager.managerId);
        ManagerVo managerVo=new ManagerVo();
        managerVo.managerType=manager.managerType;
        managerVo.token=token;
        managerVo.setManagerId(manager.getManagerId());
        if(manager.managerType.equals("book")){
            User user=userMapper.selectOne(new QueryWrapper<User>().eq("username",manager.getManagerId()));
            managerVo.setUserId(user.getId());
        }
        return ResultM.success(managerVo);
    }
   public boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");

        return false;
   }
   public ResultM addManager(RegisterInput registerInput){

       Manager manager1=managerMapper.selectById(registerInput.getName());
       if(manager1!=null)
           return ResultM.fail("Manager already exist,please change an account name",null);
       if(!registerInput.getKey1().equals(registerInput.getKey2()))
           return ResultM.fail("the two key input not consistent",null);

       Manager manager=new Manager();
       manager.managerId=registerInput.getName();
       manager.setManagerKey(DigestUtil.bcrypt(registerInput.getKey1()));
       manager.managerType=registerInput.getType();
       manager.isLocked=false;
       manager.telephoneNum=null;
       managerMapper.insert(manager);

       if(registerInput.getType().equals("book")){
           User user=new User();
           user.setPassword(DigestUtil.bcrypt(registerInput.getKey1()));
           user.setUsername(registerInput.getName());
           user.setType('S');
           user.setNickname("book");
           user.setRealName("book");
           user.setIdNumber("bookManagerID");
           userMapper.insert(user);
       }
        return ResultM.success();
   }

    @Override
    public ResultM ModifyKey(ServletRequest servletRequest, ServletResponse servletResponse, ModifyKeyInput input) {
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        String token=httpServletRequest.getHeader("Authorization");
        JwtUtil jwtUtil=new JwtUtil();
        if(jwtUtil.managerService==null)
            jwtUtil.managerService= SpringUtil.getBean(IManagerService.class);
        String uid=jwtUtil.GetUserId(token);
        if(uid==null)
            return ResultM.fail("token 错误，请重新登录",null);
        if(!input.getKey1().equals(input.getKey2())){
            return ResultM.fail("non-consistent key input",null);
        }
       Manager manager= managerMapper.selectById(uid);
        if (!DigestUtil.bcryptCheck(input.getOld_key(), manager.getManagerKey())){
            return ResultM.fail("old key input wrong",null);
        }
       manager.setManagerKey(DigestUtil.bcrypt(input.getKey1()));
       managerMapper.updateById(manager);
        return ResultM.success("successful modify key");
    }
}
