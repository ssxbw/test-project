package com.payment.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 航班对象 flight
 *
 * @date 2023-06-02
 */
public class Flight
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long flightId;

    /** 航班号 */

    private String flightNumber;

    /** 出发时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date departureTime;

    /** 到达时间 */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date arrivalTime;

    /** 出发机场 */
    private String departureAirport;

    /** 到达机场 */
    private String arrivalAirport;

    /** 最低价格 */
    private String minPrice;

    /** 是否折扣 */
    private String discount;

    /** 公司名称 */
    private String company;

    /**
     * 图片ids
     */
    private String fileIds;

    /**
     * 图片名称,回显用
     */
    private String fileNames;

    private String beginDate;

    private String endDate;



    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setFlightId(Long flightId)
    {
        this.flightId = flightId;
    }

    public Long getFlightId()
    {
        return flightId;
    }
    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber()
    {
        return flightNumber;
    }
    public void setDepartureTime(Date departureTime)
    {
        this.departureTime = departureTime;
    }

    public Date getDepartureTime()
    {
        return departureTime;
    }
    public void setArrivalTime(Date arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public Date getArrivalTime()
    {
        return arrivalTime;
    }
    public void setDepartureAirport(String departureAirport)
    {
        this.departureAirport = departureAirport;
    }

    public String getDepartureAirport()
    {
        return departureAirport;
    }
    public void setArrivalAirport(String arrivalAirport)
    {
        this.arrivalAirport = arrivalAirport;
    }

    public String getArrivalAirport()
    {
        return arrivalAirport;
    }
    public void setMinPrice(String minPrice)
    {
        this.minPrice = minPrice;
    }

    public String getMinPrice()
    {
        return minPrice;
    }
    public void setDiscount(String discount)
    {
        this.discount = discount;
    }

    public String getDiscount()
    {
        return discount;
    }
    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getCompany()
    {
        return company;
    }

}
