package com.payment.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.common.Result;
import com.payment.pojo.ElkFile;
import com.payment.pojo.FlightClass;
import com.payment.service.IElkFileService;
import com.payment.service.IFlightClassService;



/**
 * 舱位Controller
<<<<<<< HEAD
 *
=======
 *
>>>>>>> 2e26c2ac4f71b75151706aa07ea79aaf113fd8b6
 * @author ruoyi
 * @date 2023-06-02
 */
@RestController
@RequestMapping("/flightClass")
public class FlightClassController
{
	@Autowired
	private IFlightClassService flightClassService;

	@Autowired
	private IElkFileService elkFileService;
	/**
	 * 查询舱位列表
	 */

	@GetMapping("/list")
	public Map list(FlightClass flightClass) {
		Map map = new HashMap<>();
		List<FlightClass> list = flightClassService.selectFlightClassList(flightClass);
		for (int i = 0; i < list.size(); i++) {
			String ids = list.get(i).getFileIds();
			String imgUrls = "";
			if (null != ids && !"".equals(ids)) {
				String[] strArray = ids.split(",");
				for (int k = 0; k < strArray.length; k++) {
					ElkFile elkFile = elkFileService.selectElkFileById(Integer.valueOf(strArray[k]));
					if ("".equals(imgUrls)) {
						imgUrls = imgUrls.concat(elkFile.getUrl());
					} else {
						imgUrls = imgUrls.concat(",").concat(elkFile.getUrl());
					}
				}
			}
			list.get(i).setFileIds(imgUrls);
		}
		map.put("list", list);
		return map;
	}




	/**
	 * 获取舱位详细信息
	 */
	/*
	 * @GetMapping(value = "/{classId}") public AjaxResult
	 * getInfo(@PathVariable("classId") Long classId) { return
	 * success(flightClassService.selectFlightClassByClassId(classId)); }
	 */
	/**
	 * 新增舱位
	 */

	@PostMapping("/add")
	public Result<String> saveItem(FlightClass flightClass) {
		Long flightClassId = flightClassService.getMaxId();
		flightClass.setClassId(flightClassId+3);
		flightClass.setStock0(5);
		flightClass.setStock1(5);
		flightClass.setStock2(5);
		flightClass.setStock3(5);
		flightClass.setStock4(5);
		flightClass.setStock5(5);
		flightClass.setStock6(5);
		flightClass.setStock7(5);
		flightClass.setStock8(5);
		flightClass.setStock9(5);
		flightClass.setStock10(5);
		flightClass.setStock11(5);
		flightClass.setStock12(5);
		flightClass.setStock13(5);
		flightClass.setStock14(5);
		flightClassService.insertFlightClass(flightClass);
		return Result.success("添加成功");
	}

	/**
	 * 修改舱位
	 */

	@PostMapping("/edit")
	public Result<String> editItem(FlightClass flightClass) {
		flightClassService.updateFlightClass(flightClass);
		return Result.success("修改成功");
	}
	/**
	 * 删除舱位
	 */
	@PostMapping("/delete")
	public Result<String> remove(String classId) {
		flightClassService.deleteFlightClassByClassId(Long.valueOf(classId));
		return Result.success("删除成功");
	}
}
