package com.payment.pojo;

import java.util.Date;
import java.util.List;

/**
 * 酒店对象 hotel
 *
 * @author ruoyi
 * @date 2023-06-02
 */
public class Hotel
{
    private static final long serialVersionUID = 1L;

    /** 酒店id */
    private String hotelId;

    /** 评分 */
    private String rating;

    /** 名称 */
    private String name;

    /** 地址 */
    private String address;

    /** 折扣 */
    private String discount;

    /** 星级 */
    private String star;

    /** 最低价格 */
    private String minPrice;

    /**
     * 图片ids
     */
    private String fileIds;

    /**
     * 图片名称,回显用
     */
    private String fileNames;

    private List<ElkFile> elkFiles;

    private String beginDate;

    private String endDate;


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

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public List<ElkFile> getElkFiles() {
        return elkFiles;
    }

    public void setElkFiles(List<ElkFile> elkFiles) {
        this.elkFiles = elkFiles;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public void setHotelId(String hotelId)
    {
        this.hotelId = hotelId;
    }

    public String getHotelId()
    {
        return hotelId;
    }
    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public String getRating()
    {
        return rating;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setDiscount(String discount)
    {
        this.discount = discount;
    }

    public String getDiscount()
    {
        return discount;
    }
    public void setStar(String star)
    {
        this.star = star;
    }

    public String getStar()
    {
        return star;
    }
    public void setMinPrice(String minPrice)
    {
        this.minPrice = minPrice;
    }

    public String getMinPrice()
    {
        return minPrice;
    }

}
