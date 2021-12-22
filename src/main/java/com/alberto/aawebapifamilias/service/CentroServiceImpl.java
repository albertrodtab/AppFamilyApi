package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.exception.CentroNotFoundException;
import com.alberto.aawebapifamilias.repository.CentroRepository;
import com.alberto.aawebapifamilias.repository.ResidenteRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroServiceImpl implements CentroService{

    private final Logger logger = LoggerFactory.getLogger(CentroService.class);

    @Autowired
    private CentroRepository centroRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Centro patchCentro(long id, String telefono) throws CentroNotFoundException {
        Centro centro = centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
        centro.setTelefono(telefono);
        return centroRepository.save(centro);
    }

    @Override
    public Centro addCentro(Centro centro) {
        return centroRepository.save(centro);
    }

    @Override
    public Centro findCentro(String nombre) throws CentroNotFoundException {
        return (Centro) centroRepository.findByNombre(nombre).
                orElseThrow(CentroNotFoundException::new);
    }

    @Override
    public Centro findCentro(long id) throws CentroNotFoundException {
        return centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
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
    public Centro removeCentro(long id) throws CentroNotFoundException{
        Centro centro = centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
        centroRepository.delete(centro);
        return centro;
    }


    @Override
    public Centro modifyCentro(long id, Centro newCentro) throws CentroNotFoundException {
        Centro centro = centroRepository.findById(id).
                orElseThrow(CentroNotFoundException::new);
        /*
        * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
        * para que no me cree un nuevo familiar y si realice la modificación sobre el familiar indicado.
        */
        ModelMapper mapper = new ModelMapper();
        centro = mapper.map(newCentro, Centro.class);
        return centroRepository.save(centro);
    }

    @Override
    public List<Centro> findAllCentros(String nombre, String numRegistro, String email) {
        return centroRepository.findByNombreOrNumRegistroOrEmail(nombre, numRegistro, email);
    }

    @Override
    public int numResidentes(long id) throws CentroNotFoundException {
        Centro centro = centroRepository.findById(id)
                .orElseThrow(CentroNotFoundException::new);
        return centroRepository.numResidentes(id);
    }


}
