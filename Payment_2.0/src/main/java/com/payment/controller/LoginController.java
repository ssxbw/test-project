package com.payment.controller;

import com.payment.pojo.User;
import com.payment.service.IHotelService;
import com.payment.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录Controller
<<<<<<< HEAD
 *
=======
 *
>>>>>>> 2e26c2ac4f71b75151706aa07ea79aaf113fd8b6
 * @author ruoyi
 * @date 2023-06-02
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private IHotelService hotelService;
	@Autowired
	private IUserService userService;

	/**
	 * 查询酒店列表
	 */

	@PostMapping("/info")
	public Map list(String userName,String password) {
		Map map = new HashMap<>();
		User user = userService.selectUserByUserName(userName);
		if(null != user) {
			if(password.equals(user.getPassword())) {
				map.put("user", user);
				map.put("msg", "登录成功");
				map.put("code", 2);
			}else {
				map.put("user", user);
				map.put("msg", "密码错误");
				map.put("code",1);
			}
		}else {
			map.put("msg", "账号信息错误，无此人！");
			map.put("user", user);
			map.put("code", 0);
		}
		return map;
	}



}
