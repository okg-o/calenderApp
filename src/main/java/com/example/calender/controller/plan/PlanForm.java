package com.example.calender.controller.plan;

import com.example.calender.service.plan.PlanEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PlanForm(
        @NotBlank
        @Size(max=256, message = "256文字以内で入力してください。")
        String title,
        @Size(max=256, message = "256文字以内で入力してください。")
        String description,
        @NotBlank
        String startDay,
        @NotBlank
        String startTime,
        @NotBlank
        String endDay,
        @NotBlank
        String endTime

) {
    public static PlanForm fromEntity(PlanEntity planEntity) {
        return new PlanForm(
                planEntity.title(),
                planEntity.description(),
                planEntity.startDay(),
                planEntity.startTime(),
                planEntity.endDay(),
                planEntity.endTime()
        );
    }

    public PlanEntity toEntity() {
        return new PlanEntity(null, title(), description(), startDay(), startTime(), endDay(), endTime());
    }

    public PlanEntity toEntity(long id) {
        return new PlanEntity(id, title(), description(), startDay(), startTime(), endDay(), endTime());
    }
}
