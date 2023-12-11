package com.payment.service;


import com.payment.vo.ArbitrationInput;
import com.payment.vo.ResultM;

public interface ArbitrationService {
     ResultM AddArbitration(ArbitrationInput input);
     ResultM CheckArbitration(int UserId);
}
