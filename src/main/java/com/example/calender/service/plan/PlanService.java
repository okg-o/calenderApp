package com.example.calender.service.plan;

import com.example.calender.repository.plan.PlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public List<PlanEntity> find(){
        return planRepository.select();
    }

    public Optional<PlanEntity> findById(long planId) {
        return planRepository.selectById(planId);
    }

    @Transactional
    public void create(PlanEntity newEntity) {
        planRepository.insert(newEntity);
    }

    @Transactional
    public void update(PlanEntity entity) {
        planRepository.update(entity);
    }

    @Transactional
    public void delete(long id) {
        planRepository.delete(id);
    }
}
