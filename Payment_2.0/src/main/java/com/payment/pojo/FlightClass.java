package com.payment.pojo;

import java.math.BigDecimal;

/**
 * 舱位对象 flight_class
 *
 * @author ruoyi
 * @date 2023-06-02
 */
public class FlightClass
{
    private static final long serialVersionUID = 1L;

    /** 舱位id */
    private Long classId;

    /** 航班id */
    private Long flightId;

    /** 客舱 */
    private String cabin;

    /** 价格 */
    private BigDecimal price;

    /** 是否折扣 */
    private String discount;

    /** 余量1 */
    private Integer stock0;

    /** 余量2 */
    private Integer stock1;

    /** 余量3 */
    private Integer stock2;

    /** 余量4 */
    private Integer stock3;

    /** 余量5 */
    private Integer stock4;

    /** 余量6 */
    private Integer stock5;

    /** 余量7 */
    private Integer stock6;

    /** 余量8 */
    private Integer stock7;

    /** 余量9 */
    private Integer stock8;

    /** 余量10 */
    private Integer stock9;

    /** 余量11 */
    private Integer stock10;

    /** 余量12 */
    private Integer stock11;

    /** 余量13 */
    private Integer stock12;

    /** 余量14 */
    private Integer stock13;

    /** 余量15 */
    private Integer stock14;

    /**
     * 图片ids
     */
    private String fileIds;



    public Integer getStock0() {
        return stock0;
    }

    public void setStock0(Integer stock0) {
        this.stock0 = stock0;
    }

    public Integer getStock1() {
        return stock1;
    }

    public void setStock1(Integer stock1) {
        this.stock1 = stock1;
    }

    public Integer getStock2() {
        return stock2;
    }

    public void setStock2(Integer stock2) {
        this.stock2 = stock2;
    }

    public Integer getStock3() {
        return stock3;
    }

    public void setStock3(Integer stock3) {
        this.stock3 = stock3;
    }

    public Integer getStock4() {
        return stock4;
    }

    public void setStock4(Integer stock4) {
        this.stock4 = stock4;
    }

    public Integer getStock5() {
        return stock5;
    }

    public void setStock5(Integer stock5) {
        this.stock5 = stock5;
    }

    public Integer getStock6() {
        return stock6;
    }

    public void setStock6(Integer stock6) {
        this.stock6 = stock6;
    }

    public Integer getStock7() {
        return stock7;
    }

    public void setStock7(Integer stock7) {
        this.stock7 = stock7;
    }

    public Integer getStock8() {
        return stock8;
    }

    public void setStock8(Integer stock8) {
        this.stock8 = stock8;
    }

    public Integer getStock9() {
        return stock9;
    }

    public void setStock9(Integer stock9) {
        this.stock9 = stock9;
    }

    public Integer getStock10() {
        return stock10;
    }

    public void setStock10(Integer stock10) {
        this.stock10 = stock10;
    }

    public Integer getStock11() {
        return stock11;
    }

    public void setStock11(Integer stock11) {
        this.stock11 = stock11;
    }

    public Integer getStock12() {
        return stock12;
    }

    public void setStock12(Integer stock12) {
        this.stock12 = stock12;
    }

    public Integer getStock13() {
        return stock13;
    }

    public void setStock13(Integer stock13) {
        this.stock13 = stock13;
    }

    public Integer getStock14() {
        return stock14;
    }

    public void setStock14(Integer stock14) {
        this.stock14 = stock14;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public void setClassId(Long classId)
    {
        this.classId = classId;
    }

    public Long getClassId()
    {
        return classId;
    }
    public void setFlightId(Long flightId)
    {
        this.flightId = flightId;
    }

    public Long getFlightId()
    {
        return flightId;
    }
    public void setCabin(String cabin)
    {
        this.cabin = cabin;
    }

    public String getCabin()
    {
        return cabin;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setDiscount(String discount)
    {
        this.discount = discount;
    }

    public String getDiscount()
    {
        return discount;
    }

}
