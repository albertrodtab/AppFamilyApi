package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.exception.CentroNotFoundException;

import java.util.List;

public interface CentroService {


  Centro addCentro(Centro centro);

  Centro findCentro(long id) throws CentroNotFoundException;

  List<Centro> findAll();

  List<Centro> findAllCentros(long id);

  Centro removeCentro(long id) throws CentroNotFoundException;

  Centro modifyCentro(long id, Centro newCentro) throws CentroNotFoundException;



}
