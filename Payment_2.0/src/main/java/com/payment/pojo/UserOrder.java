package com.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrder {

    @TableId(value = "order_id",type=IdType.AUTO)
    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private BigDecimal totalPrice;
    private Integer itemId;
    private Integer orderState;
    private String complaintRecord;

    private String itemName; //商品名称
    private String sellerName; //归属商家名称
    private String departureTime;//航班起飞时间

    public UserOrder(Integer buyerId, Integer sellerId, BigDecimal totalPrice, Integer itemId, Integer orderState) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.totalPrice = totalPrice;
        this.itemId = itemId;
        this.orderState = orderState;

    }
}
