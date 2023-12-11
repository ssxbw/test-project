package com.payment.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFlow {
    @TableId(type = IdType.AUTO)
    private Integer transactionId = null;
    private Integer orderId;
    private Integer orderState;
    private LocalDateTime createTime;

    private String orderStateString;

    public TransactionFlow(Integer orderId, Integer orderState, LocalDateTime createTime) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.createTime = createTime;
    }
}
