package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Comunicado;
import com.alberto.aawebapifamilias.domain.Profesional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesionalRepository extends CrudRepository<Profesional, Long> {

    Profesional findAllById(long id);
    List<Profesional> findAll();
    List<Profesional> findAllProfesionalesById(long id);

}
