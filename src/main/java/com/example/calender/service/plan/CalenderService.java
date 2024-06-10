package com.example.calender.service.plan;

import com.example.calender.controller.plan.CalenderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final PlanService planService;

    public List<CalenderDTO> create() {
        int totalBlank = 35;
        List<CalenderDTO> calender = new ArrayList<CalenderDTO>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        int beforeBlank = cal.get(Calendar.DAY_OF_WEEK) - 1;
        cal.add(Calendar.DATE, -1);
        cal.add(Calendar.DATE, -(beforeBlank));

        var planList = planService.find();

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

        }

        String month = "";
        String day = "";



        for(int j = 0; j < 35; j++){

            if(calender.get(j).getMonth() < 10){
                month = "0" + calender.get(j).getMonth();
            }else{
                month = "" + calender.get(j).getMonth();
            }
            if(calender.get(j).getDay() < 10){
                day = "0" + calender.get(j).getDay();
            }else{
                day = "" + calender.get(j).getDay();
            }

            String startDay =  calender.get(j).getYear() + "-" + month + "-" + day;

            for(int k = 0; k < planList.toArray().length; k++)
                if(startDay.equals(planList.get(k).startDay())){
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);

                    try {
                        Date date1 = sdf.parse(startDay);
                        Date date2 = sdf.parse(planList.get(k).endDay());

                        long elapsedms = date2.getTime() - date1.getTime();
                        long diff = TimeUnit.DAYS.convert(elapsedms, TimeUnit.MILLISECONDS);

                        calender.get(j).setStartDay(startDay);
                        String startTime = planList.get(k).startTime() + "～";
                        calender.get(j).setStartTime(startTime);
                        calender.get(j).setEndTime(planList.get(k).endTime());
                        calender.get(j).setTitle(planList.get(k).title());
                        calender.get(j).setTerms(diff);

                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }



                }
        }
        return calender;
    }

}