package com.example.calender.controller.plan;

import com.example.calender.service.plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class test {

    @Autowired
    PlanService planService;


    public void main(String[] args) {
        int totalBlank = 35;
        List<CalenderDTO> calender = new ArrayList<CalenderDTO>();
        Calendar cal = Calendar.getInstance();
        var year = cal.get(Calendar.YEAR);
        var month = cal.get(Calendar.MONTH) + 1;
        var day = cal.get(Calendar.DATE);

        cal.set(Calendar.DATE, 1);
        int beforeBlank = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int daysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DATE, -1);
        int beforeDaysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.DATE, -(beforeBlank));

        var planList = planService.find();



        var plan = planList
                .stream()
                .map(s ->  s.startDay())
                .collect(Collectors.toList());


        for (int i = 0; i < totalBlank; i++) {
            cal.add(Calendar.DATE, 1);
            calender.add(new CalenderDTO(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DATE),
                    "",
                    "",
                    "",
                    "",
                    "",
                    0
            ));

            System.out.println(plan);


        }
    }
}
