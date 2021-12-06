package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.repository.FamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamiliarServiceImpl implements FamiliarService{

    @Autowired
    private FamiliarRepository familiarRepository;

    @Override
    public Familiar addFamiliar(Familiar familiar) {
        return familiarRepository.save(familiar);
    }

    @Override
    public Familiar findFamiliar(long id) {
        return familiarRepository.findAllById(id);
    }

    @Override
    public List<Familiar> findAllFamiliares() {
        return familiarRepository.findAll();
    }

    @Override
    public List<Familiar> findAllFamiliares(long id) {
        return familiarRepository.findAllFamiliaresById(id);
    }

    @Override
    public Familiar removeFamiliar(long id) {
        Familiar familiar = familiarRepository.findAllById(id);
        familiarRepository.delete(familiar);
        return familiar;
    }

    @Override
    public Familiar modifyFamiliar(long id, Familiar newFamiliar) {
        Familiar familiar = familiarRepository.findAllById(id);
        familiar.setApellidos(newFamiliar.getApellidos());
        familiar.setDni(newFamiliar.getDni());
        familiar.setFechaNacimiento(newFamiliar.getFechaNacimiento());
        familiar.setNombre(newFamiliar.getNombre());
        familiar.setTelefono(newFamiliar.getTelefono());
        return familiarRepository.save(familiar);
    }


}
