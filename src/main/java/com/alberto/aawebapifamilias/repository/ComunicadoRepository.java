package com.alberto.aawebapifamilias.repository;

import com.alberto.aawebapifamilias.domain.Comunicado;
import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Profesional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends CrudRepository<Comunicado, Long> {


    List<Comunicado> findAllComunicadosById(long id);

    List<Comunicado> findAll();


    List<Comunicado> findByProfesional(Profesional profesional);


}

