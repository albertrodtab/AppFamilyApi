package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;

import java.util.List;

public interface ProfesionalService {

    Profesional addProfesional(Profesional profesional);

    Profesional findProfesional(long id) throws ProfesionalNotFoundException;

    List<Profesional> findAllProfesionales();

    List<Profesional> findAllProfesionales(long id);


    Profesional removeProfesional(long id) throws ProfesionalNotFoundException;

    Profesional modifyProfesional(long id, Profesional profesional) throws ProfesionalNotFoundException;
}
