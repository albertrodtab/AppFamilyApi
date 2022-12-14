package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Familiar;
import com.alberto.appfamilyapi.domain.Residente;
import com.alberto.appfamilyapi.exception.FamiliarNotFoundException;
import com.alberto.appfamilyapi.repository.FamiliarRepository;
import com.alberto.appfamilyapi.repository.ResidenteRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FamiliarServiceImpl implements FamiliarService{

    private final Logger logger = LoggerFactory.getLogger(FamiliarServiceImpl.class);

    @Autowired
    private FamiliarRepository familiarRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Familiar addFamiliar(Familiar familiar) {
        return familiarRepository.save(familiar);
    }

    @Override
    public Familiar findFamiliar(long id) throws FamiliarNotFoundException {
        return familiarRepository.findById(id).
                orElseThrow(FamiliarNotFoundException::new);
    }

    @Override
    public List<Familiar> findAllFamiliares(){
        return familiarRepository.findAll();
    }

    @Override
    public List<Familiar> findAllFamiliares(long id){
        return familiarRepository.findAllFamiliaresById(id);
    }

    @Override
    public Familiar removeFamiliar(long id) throws FamiliarNotFoundException {
        Familiar familiar = familiarRepository.findById(id)
                .orElseThrow(FamiliarNotFoundException::new);
        familiarRepository.delete(familiar);
        return familiar;
    }

    @Override
    public Familiar modifyFamiliar(long id, Familiar newFamiliar) throws FamiliarNotFoundException{
        Familiar familiar = familiarRepository.findById(id)
                .orElseThrow(FamiliarNotFoundException::new);
        /*
        * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
        * para que no me cree un nuevo familiar y si realice la modificaci??n sobre el familiar indicado.
        */
        ModelMapper mapper = new ModelMapper();
        familiar = mapper.map(newFamiliar, Familiar.class);
//        familiar.setApellidos(newFamiliar.getApellidos());
//        familiar.setDni(newFamiliar.getDni());
//        familiar.setFechaNacimiento(newFamiliar.getFechaNacimiento());
//        familiar.setNombre(newFamiliar.getNombre());
//        familiar.setTelefono(newFamiliar.getTelefono());
        return familiarRepository.save(familiar);
    }

    @Override
    public void addRelacion(Residente residente, Familiar familiar) {
        logger.info("Inicio addRelaci??n");
        residente.getFamiliares().add(familiar);
        familiar.getResidentes().add(residente);
        residenteRepository.save(residente);
        familiarRepository.save(familiar);

        logger.info("Fin addRelacion");
    }

    @Override
    public Familiar patchfamiliar(long id, String telefono) throws FamiliarNotFoundException {
        Familiar familiar = familiarRepository.findById(id)
                .orElseThrow(FamiliarNotFoundException::new);
        familiar.setTelefono(telefono);
        return familiarRepository.save(familiar);
    }


}
