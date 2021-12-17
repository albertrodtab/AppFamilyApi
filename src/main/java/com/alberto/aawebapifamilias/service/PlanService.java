package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.PlanDto;
import com.alberto.aawebapifamilias.exception.PlanNotFoundException;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;
import com.alberto.aawebapifamilias.exception.ResidenteNotFoundException;

import java.util.List;

public interface PlanService {

    Plan addPlan(PlanDto planDto) throws ResidenteNotFoundException, ProfesionalNotFoundException;

    Plan findPlan(long id) throws PlanNotFoundException;

    List<Plan> findAllPlanes();

    List<Plan> findAllPlanesById(long id);

    Plan removePlan(long id) throws PlanNotFoundException;

    Plan modifyPlan(long id, PlanDto planDto) throws PlanNotFoundException, ResidenteNotFoundException, ProfesionalNotFoundException ;

    List<Plan> findPlanesByResidente(Residente residente);
}
