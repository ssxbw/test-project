package com.payment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.common.Result;
import com.payment.pojo.ElkFile;
import com.payment.pojo.Flight;
import com.payment.pojo.FlightClass;
import com.payment.pojo.Hotel;
import com.payment.pojo.Room;
import com.payment.service.IElkFileService;
import com.payment.service.IFlightClassService;
import com.payment.service.IFlightService;

/**
 * 航班Controller
 *
 * @date
 */
@CrossOrigin
@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private IFlightService flightService;

	@Autowired
	private IFlightClassService flightClassService;

	@Autowired
	private IElkFileService elkFileService;

//	@InitBinder  //解决前段传过来时间的字符串解析成时间报错问题
//    public void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
//    }

	/**
	 * 查询航班列表
	 */

	@GetMapping("/list")
	public Map list(Flight flight) {
		Map map = new HashMap<>();
		List<Flight> list = flightService.selectFlightList(flight);
		for (int i = 0; i < list.size(); i++) {
			String ids = list.get(i).getFileIds();
			String imgUrls = "";
			String imgNames = "";
			if (null != ids && !"".equals(ids)) {
				String[] strArray = ids.split(",");
				for (int k = 0; k < strArray.length; k++) {
					ElkFile elkFile = elkFileService.selectElkFileById(Integer.valueOf(strArray[k]));
					if ("".equals(imgUrls)) {
						imgUrls = imgUrls.concat(elkFile.getUrl());
						imgNames = imgNames.concat(elkFile.getFileName());
					} else {
						imgUrls = imgUrls.concat(",").concat(elkFile.getUrl());
						imgNames = imgNames.concat(",").concat(elkFile.getFileName());
					}
				}
			}
			list.get(i).setFileIds(imgUrls);
			list.get(i).setFileNames(imgNames);
		}
		map.put("list", list);
		return map;
	}

	@GetMapping("/list1")
	public Map list1(Flight flight) throws ParseException {
		String metBegin = flight.getBeginDate();
		String metEnd = flight.getEndDate();
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
		int days = fun(start, end);
		System.out.println("两个时间间隔天数为：" + fun(start, end));
		Map map = new HashMap<>();

		flight.setDepartureTime(format.parse(metBegin));
		flight.setArrivalTime(format.parse(metEnd));
		List<Flight> list = flightService.selectFlightList(flight);
		FlightClass flightClass = new FlightClass();
		if (days > 0) {
			flightClass.setStock0(0);
		}
		if (days > 1) {
			flightClass.setStock1(0);
		}
		if (days > 2) {
			flightClass.setStock2(0);
		}
		if (days > 3) {
			flightClass.setStock3(0);
		}
		if (days > 4) {
			flightClass.setStock4(0);
		}
		if (days > 5) {
			flightClass.setStock5(0);
		}
		if (days > 6) {
			flightClass.setStock6(0);
		}
		if (days > 7) {
			flightClass.setStock7(0);
		}
		if (days > 8) {
			flightClass.setStock8(0);
		}
		if (days > 9) {
			flightClass.setStock9(0);
		}
		if (days > 10) {
			flightClass.setStock10(0);
		}
		if (days > 11) {
			flightClass.setStock11(0);
		}
		if (days > 12) {
			flightClass.setStock12(0);
		}
		if (days > 13) {
			flightClass.setStock13(0);
		}
		if (days > 14) {
			flightClass.setStock14(0);
		}
		List<FlightClass> flightClasses = flightClassService.selectFlightClassList(flightClass);
		List<String> flightids = new ArrayList<>();
		List<Flight> flights = new ArrayList<>();
		if (flightClasses.size() > 0) {
			for (int i = 0; i < flightClasses.size(); i++) {
				flightids.add(flightClasses.get(i).getFlightId().toString());
			}
			for (int i = 0; i < list.size(); i++) {
				if (flightids.contains(list.get(i).getFlightId().toString())) {
					flights.add(list.get(i));
				}
			}
		}

		for (int i = 0; i < flights.size(); i++) {
			String ids = flights.get(i).getFileIds();
			String imgUrls = "";
			String imgNames = "";
			if (null != ids && !"".equals(ids)) {
				String[] strArray = ids.split(",");
				for (int k = 0; k < strArray.length; k++) {
					ElkFile elkFile = elkFileService.selectElkFileById(Integer.valueOf(strArray[k]));
					if ("".equals(imgUrls)) {
						imgUrls = imgUrls.concat(elkFile.getUrl());
						imgNames = imgNames.concat(elkFile.getFileName());
					} else {
						imgUrls = imgUrls.concat(",").concat(elkFile.getUrl());
						imgNames = imgNames.concat(",").concat(elkFile.getFileName());
					}
				}
			}
			flights.get(i).setFileIds(imgUrls);
			flights.get(i).setFileNames(imgNames);
		}
		map.put("list", flights);
		return map;
	}

	/**
	 * 导出航班列表
	 */
	/*
	 * @PostMapping("/export") public void export(HttpServletResponse response,
	 * Flight flight) { List<Flight> list = flightService.selectFlightList(flight);
	 * ExcelUtil<Flight> util = new ExcelUtil<Flight>(Flight.class);
	 * util.exportExcel(response, list, "航班数据"); }
	 */

	/**
	 * 获取航班详细信息
	 */

	/*
	 * @GetMapping(value = "/{flightId}") public AjaxResult
	 * getInfo(@PathVariable("flightId") Long flightId) { return
	 * success(flightService.selectFlightByFlightId(flightId)); }
	 */

	/**
	 * 新增航班
	 * @throws ParseException
	 */
	@PostMapping("/add")
	public Result<String> saveItem(Flight flight) throws ParseException {
		String metBegin = flight.getBeginDate();
		String metEnd = flight.getEndDate();
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
		flight.setDepartureTime(format.parse(metBegin));
		flight.setArrivalTime(format.parse(metEnd));
		flightService.insertFlight(flight);
		return Result.success("添加成功");
	}

	/**
	 * 修改航班
	 * @throws ParseException
	 */

	@PostMapping("/edit")
	public Result<String> editItem(Flight flight) throws ParseException {
		String metBegin = flight.getBeginDate();
		String metEnd = flight.getEndDate();
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
		flight.setDepartureTime(format.parse(metBegin));
		flight.setArrivalTime(format.parse(metEnd));
		flightService.updateFlight(flight);
		return Result.success("修改成功");
	}

	/**
	 * 删除航班
	 */
	@PostMapping("/delete")
	public Result<String> remove(String flightId) {
		flightService.deleteFlightByFlightId(Long.valueOf(flightId));
		return Result.success("删除成功");
	}

	public int fun(String s1, String s2) {
		// ctrl+alt+/提示方法参数
		// 指定日期格式
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		// 按照指定格式转化为LocalDate对象
		LocalDate time1 = LocalDate.parse(s1, dateTimeFormatter);
		LocalDate time2 = LocalDate.parse(s2, dateTimeFormatter);
		// 调方法计算两个LocalDate的天数差
		long between = ChronoUnit.DAYS.between(time1, time2);
		return (int) between;
	}
}
