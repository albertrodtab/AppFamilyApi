package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Profesional;

import java.util.List;

public interface ProfesionalService {

    Profesional addProfesional(Profesional profesional);

    Profesional findProfesional(long id);

    List<Profesional> findAllProfesionales();

    List<Profesional> findAllProfesionales(long id);


    Profesional removeProfesional(long id);

    Profesional modifyProfesional(long id, Profesional profesional);
}
