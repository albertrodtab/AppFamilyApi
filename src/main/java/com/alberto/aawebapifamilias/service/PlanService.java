package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.PlanDto;
import com.alberto.aawebapifamilias.exception.PlanNotFoundException;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;

import java.util.List;

public interface PlanService {

    Plan addPlan(PlanDto planDto) throws  ProfesionalNotFoundException;

    Plan findPlan(long id) throws PlanNotFoundException;

    List<Plan> findAllPlanes();

    List<Plan> findAllPlanesById(long id);

    Plan removePlan(long id) throws PlanNotFoundException;

    Plan modifyPlan(long id, Plan planDto) throws PlanNotFoundException, ProfesionalNotFoundException ;

    void addParticipa(Residente residente, Plan plan);

    int numResidentes(long id)throws PlanNotFoundException;

    Plan patchPlan(long id, boolean fechaFin) throws PlanNotFoundException;
}
