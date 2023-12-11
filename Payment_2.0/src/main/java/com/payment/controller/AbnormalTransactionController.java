package com.payment.controller;


import com.payment.pojo.AbnormalTransaction;
import com.payment.pojo.UserAccount;
import com.payment.service.AbnormalTransactionService;
import com.payment.service.DataPreprocessService;
import com.payment.service.ReconciliationService;
import com.payment.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/recon/api")
public class AbnormalTransactionController {
    @Autowired
    private AbnormalTransactionService abnormalTransactionService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private ReconciliationService reconciliationService;
    @Autowired
    private DataPreprocessService dataPreprocessService;

    @RequestMapping("/abnormal")
    public List<Map<String,Object>> getAllAbnormalAccounts(){
        List<UserAccount> userAccounts = userAccountService.getAbnormalUser().getData();
        List<Map<String,Object>> rst = new ArrayList<>();
        for(UserAccount userAccount: userAccounts) {
            Map<String,Object> map = new HashMap<>();
            map.put("user_id",userAccount.getUserId());
            map.put("date",userAccount.getDate());
            map.put("balance",userAccount.getBalance());
            map.put("charge",userAccount.getCharge());
            double former_balance = userAccountService.selectFormerBalanceByUserDate
                    (userAccount.getUserId(),userAccount.getDate());
            map.put("former_balance", former_balance);
            List<AbnormalTransaction> abnormalTransactions = abnormalTransactionService.selectAbnormalTransactionByUserDate
                    (userAccount.getUserId(), userAccount.getDate()).getData();
            map.put("expected_balance",userAccount.getCharge()+
                    reconciliationService.getExpectedBalance(former_balance,userAccount.getUserId(),abnormalTransactions));
            map.put("state",userAccount.getState());
            map.put("transaction",abnormalTransactions);
            rst.add(map);
        }
        return rst;
    }

    //查看date日期的对账数据列表
    @RequestMapping("/reconlist")
    public List<AbnormalTransaction> getReconciliationList(@RequestParam("date") String date) {
        return dataPreprocessService.fetchTransaction(date);
    }

    @RequestMapping("/abnormalcount")
    public int getAbnormalCount(){
        return userAccountService.getAbnormalUser().getData().size();
    }
}
