package com.payment.service.serviceIml;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.mapper.FlightClassMapper;
import com.payment.pojo.FlightClass;
import com.payment.service.IFlightClassService;

/**
 * 舱位Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-02
 */
@Service
public class FlightClassServiceImpl implements IFlightClassService
{
    @Autowired
    private FlightClassMapper flightClassMapper;

    /**
     * 查询舱位
     *
     * @param classId 舱位主键
     * @return 舱位
     */
    @Override
    public FlightClass selectFlightClassByClassId(Long classId)
    {
        return flightClassMapper.selectFlightClassByClassId(classId);
    }

    /**
     * 查询舱位列表
     *
     * @param flightClass 舱位
     * @return 舱位
     */
    @Override
    public List<FlightClass> selectFlightClassList(FlightClass flightClass)
    {
        return flightClassMapper.selectFlightClassList(flightClass);
    }

    /**
     * 新增舱位
     *
     * @param flightClass 舱位
     * @return 结果
     */
    @Override
    public int insertFlightClass(FlightClass flightClass)
    {
        return flightClassMapper.insertFlightClass(flightClass);
    }

    /**
     * 修改舱位
     *
     * @param flightClass 舱位
     * @return 结果
     */
    @Override
    public int updateFlightClass(FlightClass flightClass)
    {
        return flightClassMapper.updateFlightClass(flightClass);
    }

    /**
     * 批量删除舱位
     *
     * @param classIds 需要删除的舱位主键
     * @return 结果
     */
    @Override
    public int deleteFlightClassByClassIds(Long[] classIds)
    {
        return flightClassMapper.deleteFlightClassByClassIds(classIds);
    }

    /**
     * 删除舱位信息
     *
     * @param classId 舱位主键
     * @return 结果
     */
    @Override
    public int deleteFlightClassByClassId(Long classId)
    {
        return flightClassMapper.deleteFlightClassByClassId(classId);
    }

    @Override
    public Long getMaxId() {
        // TODO Auto-generated method stub
        return flightClassMapper.getMaxId();
    }
}
