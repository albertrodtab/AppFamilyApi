package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.ResidenteDto;
import com.alberto.aawebapifamilias.exception.CentroNotFoundException;
import com.alberto.aawebapifamilias.exception.ResidenteNotFoundException;
import com.alberto.aawebapifamilias.repository.CentroRepository;
import com.alberto.aawebapifamilias.repository.ResidenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenteServiceImpl implements ResidenteService{

    @Autowired
    private ResidenteRepository residenteRepository;
    @Autowired
    private CentroRepository centroRepository;

    @Override
    public Residente addResidente(ResidenteDto residenteDto) throws CentroNotFoundException {
        Centro centro = centroRepository.findById(residenteDto.getCentro())
                .orElseThrow(CentroNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Residente residente = mapper.map(residenteDto, Residente.class);
        residente.setCentro(centro);
        return residenteRepository.save(residente);
    }

    @Override
    public Residente findResidente(long id) throws ResidenteNotFoundException {
        return residenteRepository.findById(id).
                orElseThrow(ResidenteNotFoundException::new);
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
    public Residente removeResidente(long id) throws ResidenteNotFoundException {
        Residente residente = residenteRepository.findById(id).
                orElseThrow(ResidenteNotFoundException::new);
        residenteRepository.delete(residente);
        return residente;
    }

    @Override
    public Residente modifyResidente(long id, Residente newResidente) throws ResidenteNotFoundException {
        Residente residente = residenteRepository.findById(id).
                orElseThrow(ResidenteNotFoundException::new);
        /*
         * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
         * para que no me cree un nuevo familiar y si realice la modificación sobre el familiar indicado.
         */
        ModelMapper mapper = new ModelMapper();
        residente = mapper.map(newResidente, Residente.class);
//        residente.setApellidos(newResidente.getApellidos());
//        residente.setNombre(newResidente.getNombre());
//        residente.setDni(newResidente.getDni());
//        residente.setFechaNacimiento(newResidente.getFechaNacimiento());
//        residente.setSexo(newResidente.getSexo());
//        residente.setSaldo(newResidente.getSaldo());
        return residenteRepository.save(residente);
    }
}
