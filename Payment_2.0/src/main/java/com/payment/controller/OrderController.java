package com.payment.controller;

import com.payment.common.CustomException;
import com.payment.common.Result;
import com.payment.pojo.*;
import com.payment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private TransactionFlowService transactionFlowService;
	@Autowired
	private CommonAccountService commonAccountService;
	@Autowired
	private UserService userService;
	@Autowired
	private IRoomService roomService;
	@Autowired
	private IHotelService hotelService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IFlightClassService flightClassService;
	@Autowired
	private IFlightService flightService;

	@PostMapping("/createOrder")
	public Result<String> createOrder(String buyerId, String paymentPassword,String roomId,String beginDate,String endDate
	) {
		try {
			Room room = roomService.selectRoomByRoomId(Integer.valueOf(roomId));

			User user = userService.getById(buyerId);

			if (!paymentPassword.equals(user.getPaymentPassword())) {
				return Result.error("付款密码错误！");
			}
			if (user.getBalance().compareTo(new BigDecimal(room.getPrice()).doubleValue()) < 0) {
				return Result.error("用户余额不足");
			}
			if (new BigDecimal(room.getPrice()).compareTo(new BigDecimal(1000)) > 0 && user.getVerificationStatus() == 0) {
				return Result.error("未经过身份验证的用户无法进行大额交易！");
			}
			UserOrder userOrder = new UserOrder(Integer.valueOf(buyerId), 90001, new BigDecimal(room.getPrice()), room.getRoomId(),
					OrderState.PAYMENT);
			userOrderService.save(userOrder);
			TransactionFlow transactionFlow = new TransactionFlow(userOrder.getOrderId(), userOrder.getOrderState(),
					LocalDateTime.now());
			transactionFlowService.save(transactionFlow);// 生成交易流程
			CommonAccount commonAccount = new CommonAccount(userOrder.getOrderId(), userOrder.getTotalPrice());
			commonAccountService.save(commonAccount);
			// 用户余额减
			user.setBalance(user.getBalance()-(new BigDecimal(room.getPrice()).doubleValue()));
			userService.updateById(user);


			String start = beginDate.replace("-", ".");
			String end = endDate.replace("-", ".");
			int days = fun(start,end);
			System.out.println("两个时间间隔天数为："+fun(start,end));
			boolean flag = true;
			if(days>0) {
				if(room.getStock0()<=0){
					flag = false;
				}
			}
			if(days>1) {
				if(room.getStock0()<=0||room.getStock1()<=0){
					flag = false;
				}
			}
			if(days>2) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0){
					flag = false;
				}
			}
			if(days>3) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0){
					flag = false;
				}
			}
			if(days>4) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0){
					flag = false;
				}
			}
			if(days>5) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0){
					flag = false;
				}
			}
			if(days>6) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0){
					flag = false;
				}
			}
			if(days>7) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0){
					flag = false;
				}
			}
			if(days>8) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0){
					flag = false;
				}
			}
			if(days>9) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0||room.getStock9()<=0){
					flag = false;
				}
			}
			if(days>10) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0||room.getStock9()<=0||room.getStock10()<=0){
					flag = false;
				}
			}
			if(days>11) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0||room.getStock9()<=0||room.getStock10()<=0||room.getStock11()<=0){
					flag = false;
				}
			}
			if(days>12) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0||room.getStock9()<=0||room.getStock10()<=0||room.getStock11()<=0||room.getStock12()<=0){
					flag = false;
				}
			}
			if(days>13) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0||room.getStock9()<=0||room.getStock10()<=0||room.getStock11()<=0||room.getStock12()<=0||room.getStock13()<=0){
					flag = false;
				}
			}
			if(days>14) {
				if(room.getStock0()<=0||room.getStock1()<=0||room.getStock2()<=0||room.getStock3()<=0||room.getStock4()<=0||room.getStock5()<=0||room.getStock6()<=0||room.getStock7()<=0||room.getStock8()<=0||room.getStock9()<=0||room.getStock10()<=0||room.getStock11()<=0||room.getStock12()<=0||room.getStock13()<=0||room.getStock14()<=0){
					flag = false;
				}
			}
			if(flag==true){
				if(days>0) {
					room.setStock0(room.getStock0()-1);
				}
				if(days>1) {
					room.setStock1(room.getStock1()-1);
				}
				if(days>2) {
					room.setStock2(room.getStock2()-1);
				}
				if(days>3) {
					room.setStock3(room.getStock3()-1);
				}
				if(days>4) {
					room.setStock4(room.getStock4()-1);
				}
				if(days>5) {
					room.setStock5(room.getStock5()-1);
				}
				if(days>6) {
					room.setStock6(room.getStock6()-1);
				}
				if(days>7) {
					room.setStock7(room.getStock7()-1);
				}
				if(days>8) {
					room.setStock8(room.getStock8()-1);
				}
				if(days>9) {
					room.setStock9(room.getStock9()-1);
				}
				if(days>10) {
					room.setStock10(room.getStock10()-1);
				}
				if(days>11) {
					room.setStock11(room.getStock11()-1);
				}
				if(days>12) {
					room.setStock12(room.getStock12()-1);
				}
				if(days>13) {
					room.setStock13(room.getStock13()-1);
				}
				if(days>14) {
					room.setStock14(room.getStock14()-1);
				}
				roomService.updateRoom(room);
			}else{
				return Result.error("付款失败！");
			}
//			LambdaQueryWrapper<UserOrder> wrapper = new LambdaQueryWrapper<>();
//			wrapper.eq(UserOrder::getBuyerId, buyerId).eq(UserOrder::getItemId, room.getRoomId())
//					.eq(UserOrder::getOrderState, OrderState.PAYMENT);
//			List<UserOrder> list = userOrderService.list(wrapper);
//			list.forEach((temp) -> {
//				TransactionFlow transactionFlow = new TransactionFlow(temp.getOrderId(), temp.getOrderState(),
//						LocalDateTime.now());
//				transactionFlowService.save(transactionFlow);// 生成交易流程
//				CommonAccount commonAccount = new CommonAccount(temp.getOrderId(), temp.getTotalPrice());
//				commonAccountService.save(commonAccount);
//				// 用户余额减
//				user.setBalance(user.getBalance().subtract(item.getPrice()));
//				userService.updateById(user);
//			});
//			item.setCount(item.getCount() - 1);
//			itemService.save(item);
			return Result.success("付款成功");
		} catch (Exception e) {
			throw new CustomException("付款失败！");
		}
	}

	public int fun(String s1,String s2){
		//ctrl+alt+/提示方法参数
		//指定日期格式
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		//按照指定格式转化为LocalDate对象
		LocalDate time1 = LocalDate.parse(s1,dateTimeFormatter);
		LocalDate time2 = LocalDate.parse(s2,dateTimeFormatter);
		//调方法计算两个LocalDate的天数差
		long between = ChronoUnit.DAYS.between(time1, time2);
		return (int)between;
	}

	@GetMapping("/orderList")
	public Map list(String userId,String beginDate,String endDate) {
		Map map = new HashMap<>();
		beginDate = beginDate.concat(" 00:00:00");
		endDate = endDate.concat(" 23:59:59");
		List<UserOrder> list = orderService.findjdListByUserId(userId,beginDate,endDate);

		map.put("list", list);
		return map;
	}

	@PostMapping("/cancelRoom")
	public Result cancelRoom(String orderId) {
		UserOrder userOrder = orderService.findByOrderId(Integer.valueOf(orderId));
		orderService.updateOrder(Integer.valueOf(orderId), OrderState.REFUND);//订单状态设置为退款
		User buyer = userService.getById(userOrder.getBuyerId());
		buyer.setBalance(buyer.getBalance()+(userOrder.getTotalPrice()).doubleValue());//买家收款
		iUserService.update(buyer);
		List<TransactionFlow> transactionFlow = transactionFlowService.getByOrderId(Integer.valueOf(orderId));

//		for(int i = 0; i < transactionFlow.size(); i++){
//			if(transactionFlow.get(i).getOrderState()==3){
//				transactionFlow.get(i).setOrderState(10);
//				transactionFlowService.updateById(transactionFlow.get(i));
////				transactionFlowService.save(transactionFlow.get(i));// 生成交易流程
//				TransactionFlow temp = new TransactionFlow(userOrder.getOrderId(), 0,
//						LocalDateTime.now());
//				transactionFlowService.save(temp);// fail订单
//			}
//
//		}
		TransactionFlow temp = new TransactionFlow(userOrder.getOrderId(), 10,
				LocalDateTime.now());
		transactionFlowService.save(temp);// fail订单
		try{
			sleep(10);
		}catch (Exception e){
			return Result.error("退单失败");
		}
		TransactionFlow temp_ = new TransactionFlow(userOrder.getOrderId(), 0,
				LocalDateTime.now());
		transactionFlowService.save(temp_);// fail订单
		User seller = userService.getById(userOrder.getSellerId());
		seller.setBalance(seller.getBalance()-(userOrder.getTotalPrice()).doubleValue());//卖家退款
		iUserService.update(seller);
		return Result.success("退单成功");

	}

	@PostMapping("/ratingRoom")
	public Result ratingRoom(String orderId,Integer rating) {
		UserOrder userOrder = orderService.findByOrderId(Integer.valueOf(orderId));
		Room room = roomService.selectRoomByRoomId(userOrder.getItemId());
		Hotel hotel = hotelService.selectHotelByHotelId(Long.valueOf(String.valueOf(room.getHotelId())));
		hotel.setRating(rating.toString());
		hotelService.updateHotel(hotel);
		return Result.success("评价成功");
	}


	@PostMapping("/createFlightOrder")
	public Result<String> createFlightOrder(String buyerId, String paymentPassword,String classId,String beginDate,String endDate
	) {
		try {
			FlightClass flightClass = flightClassService.selectFlightClassByClassId(Long.valueOf(classId));

			User user = userService.getById(buyerId);

			if (!paymentPassword.equals(user.getPaymentPassword())) {
				return Result.error("付款密码错误！");
			}
			if (user.getBalance().compareTo(flightClass.getPrice().doubleValue()) < 0) {
				return Result.error("用户余额不足");
			}
			if (flightClass.getPrice().compareTo(new BigDecimal(1000)) > 0 && user.getVerificationStatus() == 0) {
				return Result.error("未经过身份验证的用户无法进行大额交易！");
			}
			UserOrder userOrder = new UserOrder(Integer.valueOf(buyerId), 90002, flightClass.getPrice(), Integer.valueOf(flightClass.getClassId().toString()),
					OrderState.PAYMENT);
			userOrderService.save(userOrder);
			TransactionFlow transactionFlow = new TransactionFlow(userOrder.getOrderId(), userOrder.getOrderState(),
					LocalDateTime.now());
			transactionFlowService.save(transactionFlow);// 生成交易流程
			CommonAccount commonAccount = new CommonAccount(userOrder.getOrderId(), userOrder.getTotalPrice());
			commonAccountService.save(commonAccount);
			// 用户余额减
			user.setBalance(user.getBalance()-(flightClass.getPrice().doubleValue()));
			userService.updateById(user);

			String metBegin = beginDate;
			String metEnd = endDate;
			// 需要转成的格式
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 注意给String型进行判空
			if (metBegin != null || metBegin.length() != 0) {
				SimpleDateFormat format1 = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
				// 开始时间 和结束时间将中国标准时间格式化为yyyy-MM-dd HH:mm:ss
				String metBeginTemp = metBegin.split(Pattern.quote("(中国标准时间)"))[0].replace("GMT+0800", "GMT+08:00");
				metBegin = format.format(format1.parse(metBeginTemp));
				System.out.println("metBegin转换后为" + metBegin);
			}
			// 第二种方法
			if (metEnd != null || metEnd.length() != 0) {
				// 开始时间 和结束时间将中国标准时间格式化为yyyy-MM-dd HH:mm:ss
				String metEndTemp = metEnd.replace("GMT", "").replaceAll("\\(.*\\)", "");
				SimpleDateFormat format2 = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH);
				metEnd = format.format(format2.parse(metEndTemp));
				System.out.println("metEnd转换后为" + metEnd);
			}
			String start = metBegin.substring(0, 10).replace("-", ".");
			String end = metEnd.substring(0, 10).replace("-", ".");

			int days = fun(start,end);
			System.out.println("两个时间间隔天数为："+fun(start,end));
			boolean flag = true;
			if(days>0) {
				if(flightClass.getStock0()<=0){
					flag = false;
				}
			}
			if(days>1) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0){
					flag = false;
				}
			}
			if(days>2) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0){
					flag = false;
				}
			}
			if(days>3) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0){
					flag = false;
				}
			}
			if(days>4) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0){
					flag = false;
				}
			}
			if(days>5) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0){
					flag = false;
				}
			}
			if(days>6) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0){
					flag = false;
				}
			}
			if(days>7) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0){
					flag = false;
				}
			}
			if(days>8) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0){
					flag = false;
				}
			}
			if(days>9) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0||flightClass.getStock9()<=0){
					flag = false;
				}
			}
			if(days>10) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0||flightClass.getStock9()<=0||flightClass.getStock10()<=0){
					flag = false;
				}
			}
			if(days>11) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0||flightClass.getStock9()<=0||flightClass.getStock10()<=0||flightClass.getStock11()<=0){
					flag = false;
				}
			}
			if(days>12) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0||flightClass.getStock9()<=0||flightClass.getStock10()<=0||flightClass.getStock11()<=0||flightClass.getStock12()<=0){
					flag = false;
				}
			}
			if(days>13) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0||flightClass.getStock9()<=0||flightClass.getStock10()<=0||flightClass.getStock11()<=0||flightClass.getStock12()<=0||flightClass.getStock13()<=0){
					flag = false;
				}
			}
			if(days>14) {
				if(flightClass.getStock0()<=0||flightClass.getStock1()<=0||flightClass.getStock2()<=0||flightClass.getStock3()<=0||flightClass.getStock4()<=0||flightClass.getStock5()<=0||flightClass.getStock6()<=0||flightClass.getStock7()<=0||flightClass.getStock8()<=0||flightClass.getStock9()<=0||flightClass.getStock10()<=0||flightClass.getStock11()<=0||flightClass.getStock12()<=0||flightClass.getStock13()<=0||flightClass.getStock14()<=0){
					flag = false;
				}
			}
			if(flag==true){
				if(days>0) {
					flightClass.setStock0(flightClass.getStock0()-1);
				}
				if(days>1) {
					flightClass.setStock1(flightClass.getStock1()-1);
				}
				if(days>2) {
					flightClass.setStock2(flightClass.getStock2()-1);
				}
				if(days>3) {
					flightClass.setStock3(flightClass.getStock3()-1);
				}
				if(days>4) {
					flightClass.setStock4(flightClass.getStock4()-1);
				}
				if(days>5) {
					flightClass.setStock5(flightClass.getStock5()-1);
				}
				if(days>6) {
					flightClass.setStock6(flightClass.getStock6()-1);
				}
				if(days>7) {
					flightClass.setStock7(flightClass.getStock7()-1);
				}
				if(days>8) {
					flightClass.setStock8(flightClass.getStock8()-1);
				}
				if(days>9) {
					flightClass.setStock9(flightClass.getStock9()-1);
				}
				if(days>10) {
					flightClass.setStock10(flightClass.getStock10()-1);
				}
				if(days>11) {
					flightClass.setStock11(flightClass.getStock11()-1);
				}
				if(days>12) {
					flightClass.setStock12(flightClass.getStock12()-1);
				}
				if(days>13) {
					flightClass.setStock13(flightClass.getStock13()-1);
				}
				if(days>14) {
					flightClass.setStock14(flightClass.getStock14()-1);
				}
				flightClassService.updateFlightClass(flightClass);
			}else{
				return Result.error("付款失败！");
			}

			return Result.success("付款成功");
		} catch (Exception e) {
			throw new CustomException("付款失败！");
		}
	}

	@GetMapping("/hborderList")
	public Map hborderList(String userId,String beginDate,String endDate) {
		Map map = new HashMap<>();
		beginDate = beginDate.concat(" 00:00:00");
		endDate = endDate.concat(" 23:59:59");
		List<UserOrder> list = orderService.findhbListByUserId(userId,beginDate,endDate);

		map.put("list", list);
		return map;
	}


	@PostMapping("/cancleFlightClass")
	public Result cancleFlightClass(String orderId) throws ParseException {
		UserOrder userOrder = orderService.findByOrderId(Integer.valueOf(orderId));
		String departureTime = userOrder.getDepartureTime().concat(" 00:00:00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = sdf.parse(departureTime);
		Date now = new Date();
		long cha = start.getTime() - now.getTime();
		double result = cha * 1.0 / (1000 * 60 * 60);
		if(result>24){
			orderService.updateOrder(Integer.valueOf(orderId), OrderState.REFUND);//订单状态设置为退款
			User buyer = userService.getById(userOrder.getBuyerId());
			buyer.setBalance(buyer.getBalance()+(userOrder.getTotalPrice().doubleValue()));//买家收款
			iUserService.update(buyer);
			TransactionFlow temp = new TransactionFlow(userOrder.getOrderId(), 10,
					LocalDateTime.now());
			transactionFlowService.save(temp);// fail订单
			try{
				sleep(10);
			}catch (Exception e){
				return Result.error("退单失败");
			}
			TransactionFlow temp_ = new TransactionFlow(userOrder.getOrderId(), 0,
					LocalDateTime.now());
			transactionFlowService.save(temp_);// fail订单
			User seller = userService.getById(userOrder.getSellerId());
			seller.setBalance(seller.getBalance()-(userOrder.getTotalPrice().doubleValue()));//卖家退款
			iUserService.update(seller);
			return Result.success("退单成功");
		}else{
			return Result.error("请在起飞24小时前退单");
		}


	}

	@PostMapping("/ratingFlightClass")
	public Result ratingFlightClass(String orderId,Integer rating) {
		UserOrder userOrder = orderService.findByOrderId(Integer.valueOf(orderId));

		Room room = roomService.selectRoomByRoomId(userOrder.getItemId());
		Hotel hotel = hotelService.selectHotelByHotelId(Long.valueOf(String.valueOf(room.getHotelId())));
		hotel.setRating(rating.toString());
		hotelService.updateHotel(hotel);
		return Result.success("评价成功");
	}
}
