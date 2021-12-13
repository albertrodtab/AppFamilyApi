package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Centro;

import java.util.List;

public interface CentroService {


  Centro addCentro(Centro centro);

  Centro findCentro(long id);

  List<Centro> findAll();

  List<Centro> findAllCentros(long id);

  Centro removeCentro(long id);

  Centro modifyCentro(long id, Centro newCentro);



}
