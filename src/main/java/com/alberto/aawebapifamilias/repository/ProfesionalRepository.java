package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Profesional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends CrudRepository<Profesional, Long> {

}
