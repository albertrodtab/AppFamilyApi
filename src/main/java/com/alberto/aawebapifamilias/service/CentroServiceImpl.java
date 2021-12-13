package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.FamiliarDto;
import com.alberto.aawebapifamilias.exception.FamiliarNotFoundException;
import com.alberto.aawebapifamilias.repository.CentroRepository;
import com.alberto.aawebapifamilias.repository.FamiliarRepository;
import com.alberto.aawebapifamilias.repository.ResidenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroServiceImpl implements CentroService{

    @Autowired
    private CentroRepository centroRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Centro addCentro(Centro centro) {
        return centroRepository.save(centro);
    }

    @Override
    public Centro findCentro(long id) {
        return centroRepository.findAllById(id);

    }

    @Override
    public List<Centro> findAll(){
        return centroRepository.findAll();
    }

    @Override
    public List<Centro> findAllCentros(long id){
        return centroRepository.findAllCentrosById(id);
    }

    @Override
    public Centro removeCentro(long id) {
        Centro centro = centroRepository.findAllById(id);
        centroRepository.delete(centro);
        return centro;
    }


    @Override
    public Centro modifyCentro(long id, Centro newCentro) {
        Centro centro = centroRepository.findAllById(id);
        /*
        * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
        * para que no me cree un nuevo familiar y si realice la modificación sobre el familiar indicado.
        */
        ModelMapper mapper = new ModelMapper();
        centro = mapper.map(newCentro, Centro.class);
        return centroRepository.save(centro);
    }


}
