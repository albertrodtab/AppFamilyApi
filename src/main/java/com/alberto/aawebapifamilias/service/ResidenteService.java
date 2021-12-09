package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Residente;

import java.util.List;

public interface ResidenteService {

    Residente addResidente(Residente residente);

    Residente findResidente(long id);

    List<Residente> findAllResidentes();

    List<Residente> findAllResidentes(long id);

    Residente removeResidente(long id);

    Residente modifyResidente(long id, Residente residente);
}
