package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Comunicado;
import com.alberto.appfamilyapi.domain.Profesional;
import com.alberto.appfamilyapi.domain.dto.ComunicadoDto;
import com.alberto.appfamilyapi.exception.ComunicadoNotFoundException;
import com.alberto.appfamilyapi.exception.ProfesionalNotFoundException;
import com.alberto.appfamilyapi.repository.ComunicadoRepository;
import com.alberto.appfamilyapi.repository.ProfesionalRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComunicadoServiceImpl implements ComunicadoService{

    private final Logger logger = LoggerFactory.getLogger(ComunicadoServiceImpl.class);

    @Autowired
    private ComunicadoRepository comunicadoRepository;

    @Autowired
    private ProfesionalRepository profesinalRepository;

    @Override
    public Comunicado addComunicado(ComunicadoDto comunicadoDto) throws ProfesionalNotFoundException {
        Profesional profesional = profesinalRepository.findById(comunicadoDto.getProfesional()).
                orElseThrow(ProfesionalNotFoundException::new);
        ModelMapper mapper = new ModelMapper();
        Comunicado comunicado = mapper.map(comunicadoDto, Comunicado.class);
        comunicado.setProfesional(profesional);
        return comunicadoRepository.save(comunicado);
    }

    @Override
    public Comunicado findComunicado (long id) throws ComunicadoNotFoundException {
        return comunicadoRepository.findById(id).
                orElseThrow(ComunicadoNotFoundException::new);
    }

    @Override
    public List<Comunicado> findAllComunicados(){
        return comunicadoRepository.findAll();
    }

    @Override
    public List<Comunicado> findAllComunicados(long id){
        return comunicadoRepository.findAllComunicadosById(id);
    }

    @Override
    public Comunicado removeComunicado(long id) throws ComunicadoNotFoundException {
        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(ComunicadoNotFoundException::new);
        comunicadoRepository.delete(comunicado);
        return comunicado;
    }

    @Override
    public Comunicado modifyComunicado(long id, ComunicadoDto comunicadoDto) throws ComunicadoNotFoundException, ProfesionalNotFoundException{
        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(ComunicadoNotFoundException::new);
        Profesional profesional = profesinalRepository.findById(comunicadoDto.getProfesional()).
                orElseThrow(ProfesionalNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        comunicado = mapper.map(comunicadoDto, Comunicado.class);
        comunicado.setId(id);
        comunicado.setProfesional(profesional);
        return comunicadoRepository.save(comunicado);
    }

    @Override
    public Comunicado patchComunicado(long id, String descripcion) throws ComunicadoNotFoundException {
        Comunicado comunicado = comunicadoRepository.findById(id)
                .orElseThrow(ComunicadoNotFoundException::new);
        comunicado.setDescripcion(descripcion);
        return comunicadoRepository.save(comunicado);
    }

    @Override
    public List<Comunicado> findComunicado(Profesional profesional) {
        return comunicadoRepository.findByProfesional(profesional);
    }


}
