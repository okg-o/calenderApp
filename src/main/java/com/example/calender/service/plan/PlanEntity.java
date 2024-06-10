package com.example.calender.service.plan;

public record PlanEntity(
        Long id,
        String title,
        String description,
        String startDay,
        String startTime,
        String endDay,
        String endTime
){
}
