package com.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan
@EnableScheduling
public class PaymentApplication {

    public static void main(String[] args) {
//        BaseContext.setCurrentId(1);
//        BaseContext.setCurrentType(0);
        SpringApplication.run(PaymentApplication.class, args);
    }

}
