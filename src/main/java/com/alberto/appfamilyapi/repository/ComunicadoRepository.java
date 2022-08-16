package com.alberto.appfamilyapi.repository;

import com.alberto.appfamilyapi.domain.Comunicado;
import com.alberto.appfamilyapi.domain.Profesional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends CrudRepository<Comunicado, Long> {


    List<Comunicado> findAllComunicadosById(long id);

    List<Comunicado> findAll();


    List<Comunicado> findByProfesional(Profesional profesional);


}

