package com.payment.service;

import java.util.List;

import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.Hotel;

/**
 * 酒店Service接口
 *
 * @author ruoyi
 * @date 2023-06-02
 */
public interface IHotelService
{
    /**
     * 查询酒店
     *
     * @param hotelId 酒店主键
     * @return 酒店
     */
    public Hotel selectHotelByHotelId(Long hotelId);

    /**
     * 查询酒店列表
     *
     * @param hotel 酒店
     * @return 酒店集合
     */
    public List<Hotel> selectHotelList(Hotel hotel);

    /**
     * 新增酒店
     *
     * @param hotel 酒店
     * @return 结果
     */
    public int insertHotel(Hotel hotel);

    /**
     * 修改酒店
     *
     * @param hotel 酒店
     * @return 结果
     */
    public int updateHotel(Hotel hotel);

    /**
     * 批量删除酒店
     *
     * @param hotelIds 需要删除的酒店主键集合
     * @return 结果
     */
    public int deleteHotelByHotelIds(Long[] hotelIds);

    /**
     * 删除酒店信息
     *
     * @param hotelId 酒店主键
     * @return 结果
     */
    public int deleteHotelByHotelId(Long hotelId);

}
