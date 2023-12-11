package com.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.payment.pojo.Hotel;
import com.payment.pojo.User;

/**
 * 酒店Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-02
 */
@Mapper
public interface IUserMapper
{

	public User selectUserByUserName(String userName);

	public User getById(int id);

	public void update(User user);

	public void updateBalanceById(String balance, String id);

}
