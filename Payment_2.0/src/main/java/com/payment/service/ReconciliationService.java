package com.payment.service;


import com.payment.pojo.AbnormalTransaction;

import java.util.List;

public interface ReconciliationService {
    static String cutoffTime = "1:00:00";
    public void   reconciliation(String date);

    /* 传入double型的balance，以及AbnormalTransation的一个List, 以及用户id，需要得到double类型的balance - assumption*/
    public double getExpectedBalance(double balance, int userid, List<AbnormalTransaction> list);

}
