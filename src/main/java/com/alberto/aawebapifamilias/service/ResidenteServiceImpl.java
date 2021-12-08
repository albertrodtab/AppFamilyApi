package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenteServiceImpl implements ResidenteService{

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Residente addResidente(Residente residente) {
        return residenteRepository.save(residente);
    }

    @Override
    public Residente findResidente(long id) {
        return residenteRepository.findAllById(id);
    }

    @Override
    public List<Residente> findAllResidentes() {
        return residenteRepository.findAll();
    }

    @Override
    public List<Residente> findAllResidentes(long id) {
        return residenteRepository.findAllResidentesById(id);
    }

    @Override
    public Residente removeResidente(long id) {
        Residente residente = residenteRepository.findAllById(id);
        residenteRepository.delete(residente);
        return residente;
    }

    @Override
    public Residente modifyResidente(long id, Residente newResidente) {
        Residente residente = residenteRepository.findAllById(id);
        residente.setApellidos(newResidente.getApellidos());
        residente.setNombre(newResidente.getNombre());
        residente.setDni(newResidente.getDni());
        residente.setFechaNacimiento(newResidente.getFechaNacimiento());
        residente.setSexo(newResidente.getSexo());
        residente.setSaldo(newResidente.getSaldo());
        return residenteRepository.save(residente);
    }
}
