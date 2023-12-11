package com.payment.service;

import java.util.List;

import com.payment.pojo.Hotel;
import com.payment.pojo.User;

/**
 * 用户Service接口
 *
 * @date 2023-06-02
 */
public interface IUserService
{
    /**
     * 查询用户
     *
     */
    public User selectUserByUserName(String userName);

    public User getById(int i);

    public void update(User user);
}
