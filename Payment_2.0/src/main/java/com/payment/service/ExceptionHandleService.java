package com.payment.service;

import com.payment.pojo.AbnormalTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExceptionHandleService {
    boolean editAccountBackupBalance(int user_id, String date, double val);

    boolean freezeAccount(int user_id, String date, int time);

    boolean confirmNormal(int user_id, String date);

    @Transactional(rollbackFor = Exception.class)
    boolean reconciliationForAccount(int user_id, String date);

    @Transactional(rollbackFor = Exception.class)
    boolean rollbackTransactionFlow(List<AbnormalTransaction> abnormalTransactions);
}
