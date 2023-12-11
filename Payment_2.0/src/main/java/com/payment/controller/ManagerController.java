package com.payment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.payment.pojo.User;
import com.payment.service.ArbitrationService;
import com.payment.service.IManagerService;
import com.payment.service.UserService;
import com.payment.vo.*;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController//使用json格式传递数据,返回类自动处理为json格式字符串
@Slf4j//lombok
@RequestMapping("/admin")
public class ManagerController {

    @Autowired//框架加载时找到此Mapper并进行实例化
    private IManagerService managerService;
    @Autowired
    private ArbitrationService arbitrationService;
    @Autowired
    private UserService userService;


    @PostMapping ("/login")
    public ResultM Login(@RequestBody ManagerInput managerInput){
        log.info("managerInput={}",managerInput);
    ResultM result=managerService.Verify(managerInput);
    return result;
    }

    @PostMapping("/register")
    public ResultM Add(@RequestBody RegisterInput registerInput){
        log.info("admin={}",registerInput);

        ResultM result=managerService.addManager(registerInput);
        return result;
    }
    @PostMapping("/arbitrate")
    public ResultM arbitrate(@RequestBody ArbitrationInput input){
        log.info("arbitration input={}",input);
        ResultM result=arbitrationService.AddArbitration(input);
        return result;
    }
    @GetMapping("/user")
    public ResultM checkArbitration(@RequestParam int userid){
        ResultM result=arbitrationService.CheckArbitration(userid);
        return result;
    }
    @PostMapping("/modify")
    public ResultM ModifyKey(ServletRequest servletRequest, ServletResponse servletResponse, @RequestBody ModifyKeyInput input){
        return managerService.ModifyKey(servletRequest,servletResponse,input);
       // return Result.success();
    }
    @PostMapping("/userlist")
    public ResultM userList(){
        Page<User> page= new Page<>(1,200);
        IPage<User> pageResult=userService.page(page);//输入获取的页数和每页记录数，调用service.page实现查询
        List<User> userInfoList=pageResult.getRecords();//获取结果
        List<UserVo> voList=new ArrayList<>();
        for(User userInfo:userInfoList){//查询结果转化为输出结果
            UserVo userVo=new UserVo();
            BeanUtils.copyProperties(userInfo,userVo);//复制成员变量
            voList.add(userVo);
        }
        List<UserVo2> vo2List=new ArrayList<>();
        for(UserVo userVo: voList){
            UserVo2 userVo2=new UserVo2();
            userVo2.setId(userVo.getId());
            userVo2.setType(userVo.getType());
            userVo2.setEmail(userVo.getEmail());
            userVo2.setPhone(userVo.getPhone());
            userVo2.setUsername(userVo.getUsername());
            userVo2.setVipValue(userVo.getVipValue());
            ResultM result=arbitrationService.CheckArbitration(userVo.getId());
            if(result.code==1){
                userVo2.setIsBlocked(false);
                userVo2.setEndDate("null");
            }else {
                userVo2.setIsBlocked(true);
                Long endTime=(Long) result.data;
                //这个是你要转成后的时间的格式
                SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 时间戳转换成时间
                String sd = sdff.format(new Date(endTime));
                userVo2.setEndDate(sd);
            }
            vo2List.add(userVo2);
        }
        return ResultM.success(vo2List);
    }

}
