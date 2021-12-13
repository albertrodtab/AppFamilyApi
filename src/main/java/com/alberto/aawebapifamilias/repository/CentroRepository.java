package com.alberto.aawebapifamilias.repository;


import com.alberto.aawebapifamilias.domain.Centro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroRepository extends CrudRepository<Centro, Long> {


    Centro findAllById(long id);

    List<Centro> findAll();

    List<Centro> findAllCentrosById(long id);

}

