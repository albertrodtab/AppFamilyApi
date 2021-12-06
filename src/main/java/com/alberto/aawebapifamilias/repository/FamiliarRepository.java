package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Familiar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends CrudRepository<Familiar, Long> {

}
