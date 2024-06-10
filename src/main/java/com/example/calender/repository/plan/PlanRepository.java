package com.example.calender.repository.plan;


import com.example.calender.service.plan.PlanEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PlanRepository {

    @Select("SELECT id, title, description, startDay, startTime, endDay, endTime FROM plans")
    List<PlanEntity>  select();

    @Select("SELECT id, title, description, startDay, startTime, endDay, endTime FROM plans WHERE id = #{planId}")
    Optional<PlanEntity> selectById(@Param("planId") long planId);

    @Insert("""
    INSERT INTO plans (title, description, startDay, startTime, endDay, endTime)
    VALUES (#{plan.title}, #{plan.description}, #{plan.startDay}, #{plan.startTime}, #{plan.endDay}, #{plan.endTime})
    """)
    void insert(@Param("plan") PlanEntity newEntity);

    @Update("""
            UPDATE plans
            SET
                title = #{plan.title},
                description = #{plan.description},
                startDay = #{plan.startDay},
                startTime = #{plan.startTime},
                endDay = #{plan.endDay},
                endTime = #{plan.endTime}
            WHERE
                id = #{plan.id}
            """)
    void update(@Param("plan") PlanEntity entity);

    @Delete("DELETE FROM plans WHERE id = #{planId}")
    void delete(@Param("planId") long id);
}
