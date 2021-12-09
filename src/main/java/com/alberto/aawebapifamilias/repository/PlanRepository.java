package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Residente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {

    Plan findAllById(long id);
    List<Plan> findAll();
    List<Plan> findAllPlanesById(long id);

    List<Plan> findByResidente(Residente residente);
}
