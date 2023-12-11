package com.payment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDto extends UserOrder{
    private String itemDescription;
    private String buyer_name;
    private String seller_name;
    private String orderStateString;
    private List<TransactionFlow> transactionFlowList;
}
