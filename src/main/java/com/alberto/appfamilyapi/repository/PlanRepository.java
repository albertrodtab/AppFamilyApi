package com.alberto.appfamilyapi.repository;

import com.alberto.appfamilyapi.domain.Plan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {

    Plan findAllById(long id);

    List<Plan> findAll();

    List<Plan> findAllPlanesById(long id);

    // Contar los residentes totales de un centro
    @Query(value = "SELECT COUNT(*) FROM \"planes_residentes\" WHERE \"planes_id\" = ?1", nativeQuery = true)
    int numResidentes(long planId);


}

