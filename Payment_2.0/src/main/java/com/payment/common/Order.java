package com.payment.common;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Order {
    private int orderId;
    private int sellerId;
    private int itemId;
    private String seller;
    private String item;
    private Double price;
    private int state;
    private Date time;
}
