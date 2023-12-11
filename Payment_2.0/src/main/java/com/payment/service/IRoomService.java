package com.payment.service;

import java.util.List;

import com.payment.pojo.Room;

/**
 * 酒店房间Service接口
 * 
 * @author ruoyi
 * @date 2023-06-02
 */
public interface IRoomService 
{
    /**
     * 查询酒店房间
     * 
     * @param roomId 酒店房间主键
     * @return 酒店房间
     */
    public Room selectRoomByRoomId(Integer roomId);

    /**
     * 查询酒店房间列表
     * 
     * @param room 酒店房间
     * @return 酒店房间集合
     */
    public List<Room> selectRoomList(Room room);

    /**
     * 新增酒店房间
     * 
     * @param room 酒店房间
     * @return 结果
     */
    public int insertRoom(Room room);

    /**
     * 修改酒店房间
     * 
     * @param room 酒店房间
     * @return 结果
     */
    public int updateRoom(Room room);

    /**
     * 批量删除酒店房间
     * 
     * @param roomIds 需要删除的酒店房间主键集合
     * @return 结果
     */
    public int deleteRoomByRoomIds(Integer[] roomIds);

    /**
     * 删除酒店房间信息
     * 
     * @param roomId 酒店房间主键
     * @return 结果
     */
    public int deleteRoomByRoomId(Integer roomId);

	public Integer getMaxId();
}
