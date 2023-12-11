package com.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.payment.pojo.FlightClass;

/**
 * 舱位Mapper接口
<<<<<<< HEAD
 *
=======
 *
>>>>>>> 2e26c2ac4f71b75151706aa07ea79aaf113fd8b6
 * @author ruoyi
 * @date 2023-06-02
 */
@Mapper
public interface FlightClassMapper
{
    /**
     * 查询舱位
     *
     * @param classId 舱位主键
     * @return 舱位
     */
    public FlightClass selectFlightClassByClassId(Long classId);

    /**
     * 查询舱位列表
     *
     * @param flightClass 舱位
     * @return 舱位集合
     */
    public List<FlightClass> selectFlightClassList(FlightClass flightClass);

    /**
     * 新增舱位
     *
     * @param flightClass 舱位
     * @return 结果
     */
    public int insertFlightClass(FlightClass flightClass);

    /**
     * 修改舱位
     *
     * @param flightClass 舱位
     * @return 结果
     */
    public int updateFlightClass(FlightClass flightClass);

    /**
     * 删除舱位
     *
     * @param classId 舱位主键
     * @return 结果
     */
    public int deleteFlightClassByClassId(Long classId);

    /**
     * 批量删除舱位
     *
     * @param classIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFlightClassByClassIds(Long[] classIds);

    public Long getMaxId();
}
