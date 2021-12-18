package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.ResidenteDto;
import com.alberto.aawebapifamilias.exception.CentroNotFoundException;
import com.alberto.aawebapifamilias.exception.ResidenteNotFoundException;

import java.util.List;

public interface ResidenteService {

    void addRelacion(Residente residente, Familiar familiar);

    Residente addResidente(ResidenteDto residenteDto) throws CentroNotFoundException;

    Residente findResidente(long id) throws ResidenteNotFoundException;

    List<Residente> findAllResidentes();

    List<Residente> findAllResidentes(long id);

    Residente removeResidente(long id) throws ResidenteNotFoundException;

    Residente modifyResidente(long id, Residente residente) throws ResidenteNotFoundException;
}
