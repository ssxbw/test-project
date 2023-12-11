package com.payment.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SortMessage {
    private LocalDate StartTime;
    private LocalDate EndTime;
    private String orderStateString;
}
