package com.payment.controller;


import com.payment.pojo.UserAccount;
import com.payment.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/recon/api")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping("/allaccount")
    public List<UserAccount> getAllAccountBackup(){
        return userAccountService.selectAllAccountBackup().getData();
    }

    @RequestMapping("/account")
    public List<UserAccount> getAccountBackupByDate(@RequestParam("date") String date){
        return userAccountService.selectAccountBackupByDate(date).getData();
    }
    @RequestMapping("/account/count")
    public long getAccountBackupNumber(){
        return userAccountService.getBackupDateNumber();
    }
}
//@RequestParam("date") String date