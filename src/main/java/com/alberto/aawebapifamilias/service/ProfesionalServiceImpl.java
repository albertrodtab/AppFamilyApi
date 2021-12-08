package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.repository.ProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{

    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Override
    public Profesional addProfesional(Profesional profesional) {
        return profesionalRepository.save(profesional);
    }

    @Override
    public Profesional findProfesional(long id) {
        return profesionalRepository.findAllById(id);
    }

    @Override
    public List<Profesional> findAllProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public List<Profesional> findAllProfesionales(long id) {
        return profesionalRepository.findAllProfesionalesById(id);
    }

    @Override
    public Profesional removeProfesional(long id) {
        Profesional profesional = profesionalRepository.findAllById(id);
        profesionalRepository.delete(profesional);
        return profesional;
    }

    @Override
    public Profesional modifyProfesional(long id, Profesional newProfesional) {
        Profesional profesional = profesionalRepository.findAllById(id);
        profesional.setNombre(newProfesional.getNombre());
        profesional.setApellidos(newProfesional.getApellidos());
        profesional.setDni(newProfesional.getDni());
        profesional.setFechaNacimiento(newProfesional.getFechaNacimiento());
        profesional.setCategoria(newProfesional.getCategoria());
        return profesionalRepository.save(profesional);
    }
}
