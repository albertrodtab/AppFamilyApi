package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Plan;

import java.util.List;

public interface PlanService {

    Plan addPlan(Plan plan);

    Plan findPlan(long id);

    List<Plan> findAllPlanes();

    List<Plan> findAllPlanesById(long id);

    Plan removePlan(long id);

    Plan modifyPlan(long id, Plan plan);

}
