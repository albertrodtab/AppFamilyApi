package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {

}
