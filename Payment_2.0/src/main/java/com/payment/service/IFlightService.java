package com.payment.service;

import java.util.List;

import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.Flight;

/**
 * 航班Service接口
 *
 * @author ruoyi
 * @date 2023-06-02
 */
public interface IFlightService
{
    /**
     * 查询航班
     *
     * @param flightId 航班主键
     * @return 航班
     */
    public Flight selectFlightByFlightId(Long flightId);

    /**
     * 查询航班列表
     *
     * @param flight 航班
     * @return 航班集合
     */
    public List<Flight> selectFlightList(Flight flight);

    /**
     * 新增航班
     *
     * @param flight 航班
     * @return 结果
     */
    public int insertFlight(Flight flight);

    /**
     * 修改航班
     *
     * @param flight 航班
     * @return 结果
     */
    public int updateFlight(Flight flight);

    /**
     * 批量删除航班
     *
     * @param flightIds 需要删除的航班主键集合
     * @return 结果
     */
    public int deleteFlightByFlightIds(Long[] flightIds);

    /**
     * 删除航班信息
     *
     * @param flightId 航班主键
     * @return 结果
     */
    public int deleteFlightByFlightId(Long flightId);

}
