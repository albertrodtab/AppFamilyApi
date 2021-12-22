package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Comunicado;
import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.exception.*;
import com.alberto.aawebapifamilias.service.ComunicadoService;
import com.alberto.aawebapifamilias.service.ProfesionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProfesionalController {

    private final Logger logger = LoggerFactory.getLogger(ProfesionalController.class);

    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private ComunicadoService comunicadoService;

    @PostMapping("/profesionales")
    public Profesional addProfesional (@RequestBody Profesional profesional){
        logger.info("Inicio addFamiliar");
        Profesional newProfesional = profesionalService.addProfesional(profesional);
        logger.info("Fin addFamiliar");
        return newProfesional;
    }

    @GetMapping("/profesional/{id}")
    public Profesional getProfesional (@PathVariable long id) throws ProfesionalNotFoundException {
        Profesional profesional = profesionalService.findProfesional(id);
        return profesional;
    }

    @GetMapping("/profesionales")
    public List<Profesional> getProfesionalById (@RequestParam(name = "profesional", defaultValue = "0") long id){
        List<Profesional> profesionales;

        if(id == 0){
            profesionales = profesionalService.findAllProfesionales();
        } else {
            profesionales = profesionalService.findAllProfesionales(id);
        }
        return profesionales;
    }

    @DeleteMapping("/profesional/{id}")
    public Profesional removeProfesional (@PathVariable long id) throws ProfesionalNotFoundException {
        logger.info("Inicio removeFamiliar");
        Profesional profesional = profesionalService.removeProfesional(id);
        logger.info("Fin removeFamiliar");
        return profesional;
    }

    @PutMapping("/profesional/{id}")
    public Profesional modifyProfesional(@RequestBody Profesional profesional, @PathVariable long id) throws ProfesionalNotFoundException {
        logger.info("Inicio modifyFamiliar");
        Profesional newProfesional = profesionalService.modifyProfesional(id, profesional);
        logger.info("Fin modifyFamiliar");
        return newProfesional;
    }

    //Mostrar todos los comunicados de un profesional
    @GetMapping("/profesional/{id}/comunicados")
    public List<Comunicado> getComunicadosByProfesional(@PathVariable long id) throws ProfesionalNotFoundException {
        logger.info("Inicio getComunicadosByProfesional");
        List<Comunicado> comunicados = null;
        logger.info("Buscar por profesional " + id);
        Profesional profesional = profesionalService.findProfesional(id);
        logger.info("Profesional encontrado. Buscar comunicados");
        comunicados = comunicadoService.findComunicado(profesional);
        logger.info("Fin getComunicadosByProfesional");
        return comunicados;
    }

    // Cambiar la categoria de un profesional
    @PatchMapping("/profesional/{id}")
    public Profesional patchProfesional (@PathVariable long id, @RequestBody String categoria) throws ProfesionalNotFoundException {
        logger.info("Inicio PatchProfesional " + id);
        Profesional profesional = profesionalService.patchProfesional(id, categoria);
        logger.info("Fin patchprofesional " + id);
        return profesional;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante

    @ExceptionHandler(ProfesionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ProfesionalNotFoundException profnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", profnfe.getMessage());
        logger.error(profnfe.getMessage(), profnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "1","Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
