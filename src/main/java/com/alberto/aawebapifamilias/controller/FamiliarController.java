package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.RelacionDTO;
import com.alberto.aawebapifamilias.exception.*;
import com.alberto.aawebapifamilias.service.FamiliarService;
import com.alberto.aawebapifamilias.service.ResidenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamiliarController {

    private final Logger logger = LoggerFactory.getLogger(FamiliarController.class);

    @Autowired
    private FamiliarService familiarService;

    @Autowired
    private ResidenteService residenteService;

    @PostMapping("/familiares")
    public Familiar addFamiliar(@RequestBody Familiar familiar) {
        logger.info("Inicio addFamiliar");
        Familiar newFamiliar = familiarService.addFamiliar(familiar);
        logger.info("Fin addFamiliar");
        return newFamiliar;
    }

    /*
     * Relacionar un familiar con un residente
     * */
    @PostMapping("/relacion")
    public ResponseEntity<Response> relacion(@RequestBody RelacionDTO relacionDTO)
            throws FamiliarNotFoundException, ResidenteNotFoundException {
        logger.info("Inicio relacion");
        Residente residente = residenteService.findResidente(relacionDTO.getResidenteId());
        logger.info("Residente encontrado " + residente.getId());
        Familiar familiar = familiarService.findFamiliar(relacionDTO.getFamiliarId());
        logger.info("Familiar encontrado " + familiar.getId());
        familiarService.addRelacion(residente, familiar);


        Response response = new Response("1", "Residente añadido al familiar " +
                relacionDTO.getFamiliarId());
        logger.info("Fin relacion");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/familiar/{id}")
    public Familiar getFamiliar (@PathVariable long id) throws FamiliarNotFoundException {
        Familiar familiar = familiarService.findFamiliar(id);
        return familiar;
    }

    @GetMapping("/familiares")
    public List<Familiar> getFamiliaresById (@RequestParam(name = "familiar", defaultValue = "0") long id){
        List<Familiar> familiares;

        if(id == 0){
            familiares = familiarService.findAllFamiliares();
        } else {
            familiares = familiarService.findAllFamiliares(id);
        }
        return familiares;
    }

    @DeleteMapping("/familiar/{id}")
    public Familiar removeFamiliar (@PathVariable long id) throws FamiliarNotFoundException{
        logger.info("Inicio removeFamiliar");
        Familiar familiar = familiarService.removeFamiliar(id);
        logger.info("Fin removeFamiliar");
        return familiar;
    }

    @PutMapping("/familiar/{id}")
    public Familiar modifyFamiliar(@RequestBody Familiar familiar, @PathVariable long id) throws FamiliarNotFoundException{
        logger.info("Inicio modifyFamiliar");
        Familiar newFamiliar = familiarService.modifyFamiliar(id, familiar);
        logger.info("Fin modifyFamiliar");
        return newFamiliar;
    }

    // Cambiar el telefono de un familiar
    @PatchMapping("/familiar/{id}")
    public Familiar patchFamiliar (@PathVariable long id, @RequestBody String telefono) throws FamiliarNotFoundException {
        logger.info("Inicio PatchFamiliar " + id);
        Familiar familiar = familiarService.patchfamiliar(id, telefono);
        logger.info("Fin patchFamiliar " + id);
        return familiar;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(FamiliarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFamiliarNotFoundException(FamiliarNotFoundException fnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", fnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CentroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResidenteNotFoundException(ResidenteNotFoundException rnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "1","Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
