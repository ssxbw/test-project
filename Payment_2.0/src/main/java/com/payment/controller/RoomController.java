package com.payment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.common.Result;
import com.payment.pojo.ElkFile;
import com.payment.pojo.Hotel;
import com.payment.pojo.Room;
import com.payment.service.IElkFileService;
import com.payment.service.IRoomService;

import jakarta.servlet.http.HttpServletResponse;

/**
 * 酒店房间Controller
 *
 * @date 2023-06-02
 */
@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private IRoomService roomService;

	@Autowired
	private IElkFileService elkFileService;
	/**
	 * 查询酒店房间列表
	 */

	@GetMapping("/list")
	public Map list(Room room) {
		Map map = new HashMap<>();
		List<Room> list = roomService.selectRoomList(room);
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
	 * 获取酒店房间详细信息
	 */
	/*
	 * @GetMapping(value = "/{roomId}") public AjaxResult
	 * getInfo(@PathVariable("roomId") Integer roomId) { return
	 * success(roomService.selectRoomByRoomId(roomId)); }
	 */

	/**
	 * 新增酒店房间
	 */
	@PostMapping("/add")
	public Result<String> saveItem(Room room) {
		Integer roomId = roomService.getMaxId();
		room.setRoomId(roomId+3);
		room.setStock0(5);
		room.setStock1(5);
		room.setStock2(5);
		room.setStock3(5);
		room.setStock4(5);
		room.setStock5(5);
		room.setStock6(5);
		room.setStock7(5);
		room.setStock8(5);
		room.setStock9(5);
		room.setStock10(5);
		room.setStock11(5);
		room.setStock12(5);
		room.setStock13(5);
		room.setStock14(5);
		roomService.insertRoom(room);
		return Result.success("添加成功");
	}
	/**
	 * 修改酒店房间
	 */
	@PostMapping("/edit")
	public Result<String> editItem(Room room) {
		roomService.updateRoom(room);
		return Result.success("修改成功");
	}

	/**
	 * 删除酒店房间
	 */
	@PostMapping("/delete")
	public Result<String> remove(String roomId) {
		roomService.deleteRoomByRoomId(Integer.valueOf(roomId));
		return Result.success("删除成功");
	}
}
