package com.alberto.appfamilyapi.service;

import com.alberto.appfamilyapi.domain.Comunicado;
import com.alberto.appfamilyapi.domain.Profesional;
import com.alberto.appfamilyapi.domain.dto.ComunicadoDto;
import com.alberto.appfamilyapi.exception.ComunicadoNotFoundException;
import com.alberto.appfamilyapi.exception.ProfesionalNotFoundException;

import java.util.List;

public interface ComunicadoService {

  Comunicado addComunicado(ComunicadoDto comunicadoDto) throws ProfesionalNotFoundException;

  Comunicado findComunicado(long id) throws ComunicadoNotFoundException;

  List<Comunicado> findAllComunicados();

  List<Comunicado> findAllComunicados(long id);

  Comunicado removeComunicado(long id) throws ComunicadoNotFoundException;

  Comunicado modifyComunicado(long id, ComunicadoDto comunicadoDto) throws ComunicadoNotFoundException, ProfesionalNotFoundException;

  Comunicado patchComunicado(long id, String descripción) throws ComunicadoNotFoundException;

  List<Comunicado> findComunicado(Profesional profesional);

}
