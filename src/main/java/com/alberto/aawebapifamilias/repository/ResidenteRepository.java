package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Residente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenteRepository extends CrudRepository<Residente, Long> {

}
