package com.payment.controller;


import com.payment.common.BaseContext;
import com.payment.common.Result;
import com.payment.pojo.ElkFile;
import com.payment.pojo.Hotel;
import com.payment.pojo.Room;
import com.payment.service.IElkFileService;
import com.payment.service.IHotelService;
import com.payment.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson2.util.UUIDUtils;
import com.payment.common.Result;
import com.payment.pojo.ElkFile;
import com.payment.pojo.Hotel;
import com.payment.pojo.Item;
import com.payment.pojo.Room;
import com.payment.service.IElkFileService;
import com.payment.service.IHotelService;
import com.payment.service.IRoomService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 酒店Controller
 *
=======
import java.util.*;

/**
 * 酒店Controller
 *
>>>>>>> 2e26c2ac4f71b75151706aa07ea79aaf113fd8b6
 * @author ruoyi
 * @date 2023-06-02
 */
@CrossOrigin
@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private IHotelService hotelService;

	@Autowired
	private IElkFileService elkFileService;

	@Autowired
	private IRoomService roomService;



	@GetMapping("/list")
	public Map list(Hotel hotel) {
		Map map = new HashMap<>();
		List<Hotel> list = hotelService.selectHotelList(hotel);
		for(int i=0;i<list.size();i++) {
			String ids = list.get(i).getFileIds();
			String imgUrls = "";
			String imgNames = "";
			if(null!=ids && !"".equals(ids)) {
				String[] strArray = ids.split(",");
				for(int k=0;k<strArray.length;k++) {
					ElkFile elkFile = elkFileService.selectElkFileById(Integer.valueOf(strArray[k]));
					if("".equals(imgUrls)) {
						imgUrls=imgUrls.concat(elkFile.getUrl());
						imgNames = imgNames.concat(elkFile.getFileName());
					}else {
						imgUrls=imgUrls.concat(",").concat(elkFile.getUrl());
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

	/**
	 * 查询酒店列表
	 */

	@GetMapping("/list1")
	public Map list1(Hotel hotel) {
		String start = hotel.getBeginDate().replace("-", ".");
		String end = hotel.getEndDate().replace("-", ".");
		int days = fun(start,end);
		System.out.println("两个时间间隔天数为："+fun(start,end));
		Map map = new HashMap<>();
		List<Hotel> list = hotelService.selectHotelList(hotel);
		Room room = new Room();
		if(days>0) {
			room.setStock0(0);
		}
		if(days>1) {
			room.setStock1(0);
		}
		if(days>2) {
			room.setStock2(0);
		}
		if(days>3) {
			room.setStock3(0);
		}
		if(days>4) {
			room.setStock4(0);
		}
		if(days>5) {
			room.setStock5(0);
		}
		if(days>6) {
			room.setStock6(0);
		}
		if(days>7) {
			room.setStock7(0);
		}
		if(days>8) {
			room.setStock8(0);
		}
		if(days>9) {
			room.setStock9(0);
		}
		if(days>10) {
			room.setStock10(0);
		}
		if(days>11) {
			room.setStock11(0);
		}
		if(days>12) {
			room.setStock12(0);
		}
		if(days>13) {
			room.setStock13(0);
		}
		if(days>14) {
			room.setStock14(0);
		}
		List<Room> rooms = roomService.selectRoomList(room);
		List<String> hotelids = new ArrayList<>();
		List<Hotel> hotels = new ArrayList<>();
		if(rooms.size()>0) {
			for(int i=0;i<rooms.size();i++) {
				hotelids.add(rooms.get(i).getHotelId().toString());
			}
			for(int i=0;i<list.size();i++) {
				if(hotelids.contains(list.get(i).getHotelId())) {
					hotels.add(list.get(i));
				}
			}
		}
		for (int i = 0; i < hotels.size(); i++) {
			String ids = hotels.get(i).getFileIds();
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
			hotels.get(i).setFileIds(imgUrls);
			hotels.get(i).setFileNames(imgNames);
		}
		map.put("list", hotels);
		return map;
	}

	/**
	 * 获取酒店详细信息
	 */
	/* @GetMapping(value = "/{hotelId}") */
	/*
	 * public AjaxResult getInfo(@PathVariable("hotelId") Long hotelId) { return
	 * success(hotelService.selectHotelByHotelId(hotelId)); }
	 */

	/**
	 * 新增酒店
	 */

	@PostMapping("/add")
	public Result<String> saveItem(Hotel hotel) {
		hotelService.insertHotel(hotel);
		return Result.success("添加成功");
	}

	/**
	 * 修改酒店
	 */

	@PostMapping("/edit")
	public Result<String> editItem(Hotel hotel) {
		hotelService.updateHotel(hotel);
		return Result.success("修改成功");
	}

	/**
	 * 删除酒店
	 */

	@PostMapping("/delete")
	public Result<String> remove(String hotelId) {
		hotelService.deleteHotelByHotelId(Long.valueOf(hotelId));
		return Result.success("删除成功");
	}

	/**
	 * 通用上传请求（单个）
	 */
	@PostMapping("/upload")
	public Result uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		String newName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));

		String path = "d://save/images/";
		String xnpath = "http://localhost:80/images/";
		File saveFile = new File(path + newName);
		if (!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs();
		}
		file.transferTo(saveFile);
		String url = xnpath + newName;
		ElkFile elkFile = new ElkFile();
		elkFile.setUrl(url);
		elkFile.setFileName(fileName);
		elkFile.setNewName(newName);
		elkFile.setOldName(file.getOriginalFilename());
		elkFile.setFileSize(String.valueOf(file.getSize()));
		elkFileService.insertElkFile(elkFile);
		Result result = new Result<>();
		Map map = new HashMap<>();
		map.put("fileId", elkFile.getId());
		map.put("url", url);
		map.put("fileName", fileName);
		map.put("newFileName", newName);
		map.put("originalFilename", file.getOriginalFilename());
		result.setData(map);
		result.setCode(1);
		result.setMsg("上传成功");
		return result;
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

}
