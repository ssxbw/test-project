package com.payment.service.serviceIml;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.payment.mapper.CommonAccountMapper;
import com.payment.mapper.ItemMapper;
import com.payment.pojo.CommonAccount;
import com.payment.pojo.Item;
import com.payment.service.CommonAccountService;
import com.payment.service.ItemService;
import org.springframework.stereotype.Service;


@Service
public class CommonAccountServiceImpl extends ServiceImpl<CommonAccountMapper, CommonAccount> implements CommonAccountService {

}
