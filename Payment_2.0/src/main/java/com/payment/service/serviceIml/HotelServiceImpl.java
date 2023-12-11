package com.payment.service.serviceIml;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.HotelMapper;
import com.payment.pojo.Hotel;
import com.payment.service.IHotelService;

import static com.baomidou.mybatisplus.extension.toolkit.Db.getOne;

/**
 * 酒店Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-02
 */
@Service
public class HotelServiceImpl implements IHotelService
{
    @Autowired
    private HotelMapper hotelMapper;

    /**
     * 查询酒店
     *
     * @param hotelId 酒店主键
     * @return 酒店
     */
    @Override
    public Hotel selectHotelByHotelId(Long hotelId)
    {
        return hotelMapper.selectHotelByHotelId(hotelId);
    }

    /**
     * 查询酒店列表
     *
     * @param hotel 酒店
     * @return 酒店
     */
    @Override
    public List<Hotel> selectHotelList(Hotel hotel)
    {
        return hotelMapper.selectHotelList(hotel);
    }

    /**
     * 新增酒店
     *
     * @param hotel 酒店
     * @return 结果
     */
    @Override
    public int insertHotel(Hotel hotel)
    {
        return hotelMapper.insertHotel(hotel);
    }

    /**
     * 修改酒店
     *
     * @param hotel 酒店
     * @return 结果
     */
    @Override
    public int updateHotel(Hotel hotel)
    {
        return hotelMapper.updateHotel(hotel);
    }

    /**
     * 批量删除酒店
     *
     * @param hotelIds 需要删除的酒店主键
     * @return 结果
     */
    @Override
    public int deleteHotelByHotelIds(Long[] hotelIds)
    {
        return hotelMapper.deleteHotelByHotelIds(hotelIds);
    }

    /**
     * 删除酒店信息
     *
     * @param hotelId 酒店主键
     * @return 结果
     */
    @Override
    public int deleteHotelByHotelId(Long hotelId)
    {
        return hotelMapper.deleteHotelByHotelId(hotelId);
    }
}
