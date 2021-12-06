package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Familiar;

import java.util.List;

public interface FamiliarService {

  Familiar addFamiliar(Familiar familiar);

  Familiar findFamiliar(long id);

  List<Familiar> findAllFamiliares();

  List<Familiar> findAllFamiliares(long id);

  Familiar removeFamiliar(long id);

  Familiar modifyFamiliar(long id, Familiar familiar);
}
