package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.exception.ErrorResponse;
import com.alberto.aawebapifamilias.exception.FamiliarNotFoundException;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;
import com.alberto.aawebapifamilias.service.ProfesionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfesionalController {

    private final Logger logger = LoggerFactory.getLogger(ProfesionalController.class);

    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/profesionales")
    public Profesional addProfesional (@RequestBody Profesional profesional){
        Profesional newProfesional = profesionalService.addProfesional(profesional);
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
        Profesional profesional = profesionalService.removeProfesional(id);
        return profesional;
    }

    @PutMapping("/profesional/{id}")
    public Profesional modifyProfesional(@RequestBody Profesional profesional, @PathVariable long id) throws ProfesionalNotFoundException {
        Profesional newProfesional = profesionalService.modifyProfesional(id, profesional);
        return newProfesional;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante

    @ExceptionHandler(ProfesionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ProfesionalNotFoundException profnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", profnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "1","Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
