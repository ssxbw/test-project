package com.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.payment.pojo.TransactionFlow;
import com.payment.pojo.UserOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface TransactionFlowMapper extends BaseMapper<TransactionFlow> {
    @Transactional
    @Select("select transaction_id,order_id,order_state,create_time, order_state \n" +
            "from transaction_flow where order_id = #{order_id};")
    List<TransactionFlow> getByOrderId(Integer order_id);
}
