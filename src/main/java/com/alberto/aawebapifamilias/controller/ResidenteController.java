package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.RelacionDTO;
import com.alberto.aawebapifamilias.domain.dto.ResidenteDto;
import com.alberto.aawebapifamilias.exception.*;
import com.alberto.aawebapifamilias.service.CentroService;
import com.alberto.aawebapifamilias.service.FamiliarService;
import com.alberto.aawebapifamilias.service.PlanService;
import com.alberto.aawebapifamilias.service.ResidenteService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResidenteController {

    private final Logger logger = LoggerFactory.getLogger(ResidenteController.class);

    @Autowired
    private ResidenteService residenteService;
    @Autowired
    private FamiliarService familiarService;
    @Autowired
    private PlanService planService;
    @Autowired
    private CentroService centroService;

    //Añadir Residente
    @PostMapping("/residentes")
    public Residente addResidente (@RequestBody ResidenteDto residenteDto) throws CentroNotFoundException {
        logger.info("Inicio addResidente");
        Residente newResidente = residenteService.addResidente(residenteDto);
        logger.info("Fin addResidente");
        return newResidente;
    }
    //Consultar residente por id
    @GetMapping("/residente/{id}")
    public Residente getResidente (@PathVariable long id) throws ResidenteNotFoundException {
        Residente residente = residenteService.findResidente(id);
        return residente;
    }
    //Consultar residentes
    @GetMapping("/residentes")
    public List<Residente> getResidenteById
            (@RequestParam(name = "residente", defaultValue = "0") long id){
        List<Residente> residentes;

        if(id == 0){
            residentes = residenteService.findAllResidentes();
        } else {
            residentes = residenteService.findAllResidentes(id);
        }
        return residentes;
    }
    //Eliminar Residente
    @DeleteMapping("/residente/{id}")
    public Residente removeResidente (@PathVariable long id) throws ResidenteNotFoundException{
        logger.info("Inicio removeResidente");
        Residente residente = residenteService.removeResidente(id);
        logger.info("Fin removeResidente");
        return residente;
    }

    //Modificar residente
    @PutMapping("/residente/{id}")
    public Residente modifyResidente(@RequestBody Residente residente, @PathVariable long id) throws ResidenteNotFoundException{
        logger.info("Inicio modifyResidente");
        Residente newResidente = residenteService.modifyResidente(id, residente);
        logger.info("Fin modifyResidente");
        return newResidente;
    }

    // Actualizar el saldo de un residente
    @PatchMapping("/residente/{id}")
    public Residente patchResidente (@PathVariable long id, @RequestBody float saldo) throws ResidenteNotFoundException {
        logger.info("Start PatchResidente " + id);
        Residente residente = residenteService.patchProfesional(id, saldo);
        logger.info("End patchResidente " + id);
        return residente;
    }

    // Consultar los residentes con una saldo inferior a una cifra determinada. SQL
    @GetMapping("/residentes/{saldo}")
    public List<Residente> saldoMenor (@PathVariable float saldo) {
        logger.info("Inicio saldoMenor " + saldo);
        List<Residente> residentes = null;
        residentes = residenteService.saldoMenor(saldo);
        logger.info("Fin SaldoMenor " + saldo);
        return residentes;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(ResidenteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResidenteNotFoundException(ResidenteNotFoundException rnfe){
        ErrorResponse errorResponse = new ErrorResponse("1", rnfe.getMessage());
        //cuando devuelva una excepcion anoto un logger de error y cuando salte una excepción capturo la traza y la lanzo.
        //tambien puedo pasar la excepción completa.
        logger.error(rnfe.getMessage(), rnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CentroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCentroNotFoundException(CentroNotFoundException cnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "999","Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
