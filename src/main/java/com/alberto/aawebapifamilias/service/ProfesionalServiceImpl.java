package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;
import com.alberto.aawebapifamilias.exception.ResidenteNotFoundException;
import com.alberto.aawebapifamilias.repository.ProfesionalRepository;
import org.modelmapper.ModelMapper;
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
    public Profesional findProfesional(long id) throws ProfesionalNotFoundException{
        return profesionalRepository.findById(id).
                orElseThrow(ProfesionalNotFoundException::new);
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
    public Profesional removeProfesional(long id) throws ProfesionalNotFoundException {
        Profesional profesional = profesionalRepository.findById(id).
                orElseThrow(ProfesionalNotFoundException::new);
        profesionalRepository.delete(profesional);
        return profesional;
    }

    @Override
    public Profesional modifyProfesional(long id, Profesional newProfesional) throws ProfesionalNotFoundException {
        Profesional profesional = profesionalRepository.findById(id).
                orElseThrow(ProfesionalNotFoundException::new);
        /*
         * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
         * para que no me cree un nuevo familiar y si realice la modificación sobre el familiar indicado.
         */
        ModelMapper mapper = new ModelMapper();
        profesional = mapper.map(newProfesional, Profesional.class);
//        profesional.setNombre(newProfesional.getNombre());
//        profesional.setApellidos(newProfesional.getApellidos());
//        profesional.setDni(newProfesional.getDni());
//        profesional.setFechaNacimiento(newProfesional.getFechaNacimiento());
//        profesional.setCategoria(newProfesional.getCategoria());
        return profesionalRepository.save(profesional);
    }
}
