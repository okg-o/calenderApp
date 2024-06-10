package com.example.calender.controller.plan;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CalenderDTO{
        int year;
        int month;
        int day;
        String title;
        String startDay;
        String startTime;
        String endDay;
        String endTime;
        long terms;


}
