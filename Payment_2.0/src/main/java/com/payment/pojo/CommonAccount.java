package com.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CommonAccount {
    private Integer orderId;
    private BigDecimal totalPrice;
}
