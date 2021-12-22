package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Comunicado;
import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.domain.dto.ComunicadoDto;
import com.alberto.aawebapifamilias.exception.ComunicadoNotFoundException;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;

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
