package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Centro;
import com.alberto.appfamilyapi.exception.CentroNotFoundException;

import java.util.List;

public interface CentroService {


  Centro patchCentro(long id, String telefono) throws CentroNotFoundException;


  Centro addCentro(Centro centro);

  Centro findCentro(String nombre) throws CentroNotFoundException;

  Centro findCentro(long id) throws CentroNotFoundException;

  List<Centro> findAll();

  List<Centro> findAllCentros(long id);

  Centro removeCentro(long id) throws CentroNotFoundException;

  Centro modifyCentro(long id, Centro newCentro) throws CentroNotFoundException;


  List<Centro> findAllCentros(String nombre, String numRegistro, String email);

  int numResidentes(long id) throws CentroNotFoundException;
}
