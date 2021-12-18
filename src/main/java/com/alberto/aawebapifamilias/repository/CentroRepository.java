package com.alberto.aawebapifamilias.repository;


import com.alberto.aawebapifamilias.domain.Centro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentroRepository extends CrudRepository<Centro, Long> {


    Centro findAllById(long id);

    List<Centro> findAll();

    List<Centro> findAllCentrosById(long id);

    Optional<Object> findByNombre(String nombre);

    List<Centro> findByNombreOrNumRegistroOrEmail(String nombre, String numRegistro, String email);
}

