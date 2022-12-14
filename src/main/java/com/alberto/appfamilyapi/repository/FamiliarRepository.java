package com.alberto.appfamilyapi.repository;

import com.alberto.appfamilyapi.domain.Familiar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamiliarRepository extends CrudRepository<Familiar, Long> {

    Familiar findAllById(long id);
    List<Familiar> findAll();
    List<Familiar> findAllFamiliaresById(long id);

}

