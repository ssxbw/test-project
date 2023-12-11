package com.payment.service.serviceIml;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.mapper.ItemMapper;
import com.payment.pojo.Item;
import com.payment.service.ItemService;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    public ApiResult<String> check(Order order) {
        LambdaQueryWrapper<Item> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Item::getItemId, order.getItemId());
        Item item = getOne(lqw);
        if (item == null) return ApiResult.error("未找到商品");
        order.setItem(item.getItemDescription());
        return ApiResult.success("成功");
    }
}
