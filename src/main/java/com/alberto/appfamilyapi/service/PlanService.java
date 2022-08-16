package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Plan;
import com.alberto.appfamilyapi.domain.Residente;
import com.alberto.appfamilyapi.domain.dto.PlanDto;
import com.alberto.appfamilyapi.exception.PlanNotFoundException;
import com.alberto.appfamilyapi.exception.ProfesionalNotFoundException;

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
