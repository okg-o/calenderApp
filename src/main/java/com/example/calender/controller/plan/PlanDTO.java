package com.example.calender.controller.plan;

import com.example.calender.service.plan.PlanEntity;

public record PlanDTO(
        long id,
        String title,
        String description,
        String startDay,
        String startTime,
        String endDay,
        String endTime
){
        public static PlanDTO toDTO(PlanEntity entity) {
                return new PlanDTO(
                        entity.id(),
                        entity.title(),
                        entity.description(),
                        entity.startDay(),
                        entity.startTime(),
                        entity.endDay(),
                        entity.endTime()
                );
        }
}
