package com.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payment.pojo.TransactionFlow;
import com.payment.pojo.UserOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {
    @Transactional
    @Select("select order_id,buyer_id,seller_id,total_price,item_id,order_id,complaint_record,order_state \n" +
            "from user_order where buyer_id = #{id} or seller_id = #{id};")
    List<UserOrder> getAllById(Integer id);

    @Transactional
    @Select("select * from transaction_flow where order_id = #{orderId}")
    List<TransactionFlow> getFlowsByOrderId(Integer orderId);
}
