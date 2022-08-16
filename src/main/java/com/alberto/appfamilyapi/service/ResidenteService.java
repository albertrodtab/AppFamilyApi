package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Residente;
import com.alberto.appfamilyapi.domain.dto.ResidenteDto;
import com.alberto.appfamilyapi.exception.CentroNotFoundException;
import com.alberto.appfamilyapi.exception.ResidenteNotFoundException;

import java.util.List;

public interface ResidenteService {

    Residente addResidente(ResidenteDto residenteDto) throws CentroNotFoundException;

    Residente findResidente(long id) throws ResidenteNotFoundException;

    List<Residente> findAllResidentes();

    List<Residente> findAllResidentes(long id);

    Residente removeResidente(long id) throws ResidenteNotFoundException;

    Residente modifyResidente(long id, Residente residente) throws ResidenteNotFoundException;

    Residente patchProfesional(long id, float saldo) throws ResidenteNotFoundException;

    List<Residente> saldoMenor(float saldo);
}
