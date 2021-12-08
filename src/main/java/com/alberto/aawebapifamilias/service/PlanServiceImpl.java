package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepository planRepository;

    @Override
    public Plan addPlan(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan findPlan(long id) {
        return planRepository.findAllById(id);
    }

    @Override
    public List<Plan> findAllPlanes() {
        return planRepository.findAll();
    }

    @Override
    public List<Plan> findAllPlanesById(long id) {
        return planRepository.findAllPlanesById(id);
    }

    @Override
    public Plan removePlan(long id) {
        Plan plan = planRepository.findAllById(id);
        planRepository.delete(plan);
        return plan;
    }

    @Override
    public Plan modifyPlan(long id, Plan newPlan) {
        Plan plan = planRepository.findAllById(id);
        plan.setNombrePlan(newPlan.getNombrePlan());
        plan.setTerapia(newPlan.getTerapia());
        plan.setDescripcion(newPlan.getDescripcion());
        plan.setFechaInicio(newPlan.getFechaInicio());
        plan.setFechaFin(newPlan.getFechaFin());
        plan.setImportante(newPlan.getImportante());
        return planRepository.save(plan);
    }
}
