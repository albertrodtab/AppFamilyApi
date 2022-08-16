package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Familiar;
import com.alberto.appfamilyapi.domain.Residente;
import com.alberto.appfamilyapi.exception.FamiliarNotFoundException;

import java.util.List;

public interface FamiliarService {

  Familiar addFamiliar(Familiar familiar);

  Familiar findFamiliar(long id) throws FamiliarNotFoundException;

  List<Familiar> findAllFamiliares();

  List<Familiar> findAllFamiliares(long id);

  Familiar removeFamiliar(long id) throws FamiliarNotFoundException;

  Familiar modifyFamiliar(long id, Familiar familiar) throws FamiliarNotFoundException;

    void addRelacion(Residente residente, Familiar familiar);

  Familiar patchfamiliar(long id, String telefono)throws FamiliarNotFoundException;

  //Familiar addFamiliar(FamiliarDto familiarDto);
}
