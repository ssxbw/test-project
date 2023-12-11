package com.payment.service;

import java.util.List;

import com.payment.pojo.FlightClass;

/**
 * 舱位Service接口
 *
 * @author ruoyi
 * @date 2023-06-02
 */
public interface IFlightClassService
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
     * 批量删除舱位
     *
     * @param classIds 需要删除的舱位主键集合
     * @return 结果
     */
    public int deleteFlightClassByClassIds(Long[] classIds);

    /**
     * 删除舱位信息
     *
     * @param classId 舱位主键
     * @return 结果
     */
    public int deleteFlightClassByClassId(Long classId);

    public Long getMaxId();
}
