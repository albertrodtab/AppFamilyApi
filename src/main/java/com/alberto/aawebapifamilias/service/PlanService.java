package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.PlanDto;

import java.util.List;

public interface PlanService {

    Plan addPlan(PlanDto planDto);

    Plan findPlan(long id);

    List<Plan> findAllPlanes();

    List<Plan> findAllPlanesById(long id);

    Plan removePlan(long id);

    Plan modifyPlan(long id, Plan plan);

    List<Plan> findPlanesByResidente(Residente residente);
}
