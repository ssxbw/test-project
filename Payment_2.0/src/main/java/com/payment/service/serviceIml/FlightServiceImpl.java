package com.payment.service.serviceIml;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.payment.common.ApiResult;
import com.payment.common.Order;
import com.payment.pojo.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.FlightMapper;
import com.payment.pojo.Flight;
import com.payment.service.IFlightService;

import static com.baomidou.mybatisplus.extension.toolkit.Db.getOne;

/**
 * 航班Service业务层处理
 *
 * @date 2023-06-02
 */
@Service
public class FlightServiceImpl implements IFlightService
{
    @Autowired
    private FlightMapper flightMapper;

    /**
     * 查询航班
     *
     * @param flightId 航班主键
     * @return 航班
     */
    @Override
    public Flight selectFlightByFlightId(Long flightId)
    {
        return flightMapper.selectFlightByFlightId(flightId);
    }

    /**
     * 查询航班列表
     *
     * @param flight 航班
     * @return 航班
     */
    @Override
    public List<Flight> selectFlightList(Flight flight)
    {
        return flightMapper.selectFlightList(flight);
    }

    /**
     * 新增航班
     *
     * @param flight 航班
     * @return 结果
     */
    @Override
    public int insertFlight(Flight flight)
    {
        return flightMapper.insertFlight(flight);
    }

    /**
     * 修改航班
     *
     * @param flight 航班
     * @return 结果
     */
    @Override
    public int updateFlight(Flight flight)
    {
        return flightMapper.updateFlight(flight);
    }

    /**
     * 批量删除航班
     *
     * @param flightIds 需要删除的航班主键
     * @return 结果
     */
    @Override
    public int deleteFlightByFlightIds(Long[] flightIds)
    {
        return flightMapper.deleteFlightByFlightIds(flightIds);
    }

    /**
     * 删除航班信息
     *
     * @param flightId 航班主键
     * @return 结果
     */
    @Override
    public int deleteFlightByFlightId(Long flightId)
    {
        return flightMapper.deleteFlightByFlightId(flightId);
    }
}
