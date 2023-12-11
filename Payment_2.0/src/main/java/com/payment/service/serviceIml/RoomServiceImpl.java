package com.payment.service.serviceIml;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.RoomMapper;
import com.payment.pojo.Room;
import com.payment.service.IRoomService;

/**
 * 酒店房间Service业务层处理
 *
 * @date 2023-06-02
 */
@Service
public class RoomServiceImpl implements IRoomService
{
    @Autowired
    private RoomMapper roomMapper;

    /**
     * 查询酒店房间
     *
     * @param roomId 酒店房间主键
     * @return 酒店房间
     */
    @Override
    public Room selectRoomByRoomId(Integer roomId)
    {
        return roomMapper.selectRoomByRoomId(roomId);
    }

    /**
     * 查询酒店房间列表
     *
     * @param room 酒店房间
     * @return 酒店房间
     */
    @Override
    public List<Room> selectRoomList(Room room)
    {
        return roomMapper.selectRoomList(room);
    }

    /**
     * 新增酒店房间
     *
     * @param room 酒店房间
     * @return 结果
     */
    @Override
    public int insertRoom(Room room)
    {
        return roomMapper.insertRoom(room);
    }

    /**
     * 修改酒店房间
     *
     * @param room 酒店房间
     * @return 结果
     */
    @Override
    public int updateRoom(Room room)
    {
        return roomMapper.updateRoom(room);
    }

    /**
     * 批量删除酒店房间
     *
     * @param roomIds 需要删除的酒店房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomByRoomIds(Integer[] roomIds)
    {
        return roomMapper.deleteRoomByRoomIds(roomIds);
    }

    /**
     * 删除酒店房间信息
     *
     * @param roomId 酒店房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomByRoomId(Integer roomId)
    {
        return roomMapper.deleteRoomByRoomId(roomId);
    }

    @Override
    public Integer getMaxId() {
        // TODO Auto-generated method stub
        return roomMapper.getMaxId();
    }
}
