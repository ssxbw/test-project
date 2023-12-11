package com.payment.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
          Date  start = sdf.parse(sdf.format("2023-06-07"));
          Date end = sdf.parse(sdf.format("2023-06-07"));
     
        long startTime = start.getTime();
        long endTime = end.getTime();
        long betweenDays = (endTime - startTime) / (1000 * 3600 * 24);
       
        System.out.println("相差天数: " + Integer.parseInt(String.valueOf(betweenDays)));

	}

}
