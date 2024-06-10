package com.example.calender.controller.plan;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlanNotFoundException extends RuntimeException {
}
