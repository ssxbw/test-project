package com.payment.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Item {
    private String name;
    private BigDecimal price;
    private Integer itemId;
    private Integer sellerId;
    private String itemDescription;
    private String image;
    private Integer count;
}
