package com.payment.service.serviceIml;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.HotelMapper;
import com.payment.mapper.IUserMapper;
import com.payment.pojo.Hotel;
import com.payment.pojo.User;
import com.payment.service.IHotelService;
import com.payment.service.IUserService;

/**
 * 酒店Service业务层处理
<<<<<<< HEAD
 *
=======
 *
>>>>>>> 2e26c2ac4f71b75151706aa07ea79aaf113fd8b6
 * @author ruoyi
 * @date 2023-06-02
 */
@Service
public class IUserServiceImpl implements IUserService
{
    @Autowired
    private IUserMapper userMapper;

	@Override
	public User selectUserByUserName(String userName) {

		return userMapper.selectUserByUserName(userName);
	}


	@Override
	public User getById(int i) {

		return userMapper.getById(i);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);

	}
}
