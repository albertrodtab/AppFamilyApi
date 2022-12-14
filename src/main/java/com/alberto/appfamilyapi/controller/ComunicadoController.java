package com.alberto.appfamilyapi.controller;

import com.alberto.appfamilyapi.domain.Comunicado;
import com.alberto.appfamilyapi.domain.dto.ComunicadoDto;
import com.alberto.appfamilyapi.exception.ComunicadoNotFoundException;
import com.alberto.appfamilyapi.exception.ErrorResponse;
import com.alberto.appfamilyapi.exception.ProfesionalNotFoundException;
import com.alberto.appfamilyapi.service.ComunicadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ComunicadoController {

    private final Logger logger = LoggerFactory.getLogger(ComunicadoController.class);

    @Autowired
    private ComunicadoService comunicadoService;

    @PostMapping("/comunicados")
    public Comunicado addComunicado (@RequestBody ComunicadoDto comunicadoDto) throws ProfesionalNotFoundException{
        logger.info("Inicio addComunicado");
        Comunicado newComunicado = comunicadoService.addComunicado(comunicadoDto);
        logger.info("Fin addComunicado");
        return newComunicado;
    }

    @GetMapping("/comunicado/{id}")
    public Comunicado getComunicado (@PathVariable long id) throws ComunicadoNotFoundException {
        logger.info("Inicio getComunicado");
        Comunicado comunicado = comunicadoService.findComunicado(id);
        logger.info("Fin getComunicado");
        return comunicado;
    }

    @GetMapping("/comunicados")
    public List<Comunicado> getComunicadosById (@RequestParam(name = "comunicado", defaultValue = "0") long id){
        logger.info("Inicio getComunicadosById");
        List<Comunicado> comunicados;

        if(id == 0){
            comunicados = comunicadoService.findAllComunicados();
        } else {
            comunicados = comunicadoService.findAllComunicados(id);
        }
        logger.info("Fin getComunicadosById");
        return comunicados;
    }

    @DeleteMapping("/comunicado/{id}")
    public Comunicado removeComunicado (@PathVariable long id) throws ComunicadoNotFoundException {
        logger.info("Inicio removeComunicado");
        Comunicado comunicado = comunicadoService.removeComunicado(id);
        logger.info("Fin removeComunicado");
        return comunicado;
    }

    @PutMapping("/comunicado/{id}")
    public Comunicado modifyComunicado(@RequestBody ComunicadoDto comunicadoDto, @PathVariable long id)
            throws ComunicadoNotFoundException, ProfesionalNotFoundException{
        logger.info("Inicio modifyComunicado");
        Comunicado newComunicado = comunicadoService.modifyComunicado(id, comunicadoDto);
        logger.info("Fin modifyComunicado");
        return newComunicado;
    }

    // Cambiar la descripci??n de un comunicado
    @PatchMapping("/comunicado/{id}")
    public Comunicado patchComunicado (@PathVariable long id, @RequestBody String descripci??n)
            throws ComunicadoNotFoundException {
        logger.info("Inicio PatchComunicado " + id);
        Comunicado comunicado = comunicadoService.patchComunicado(id, descripci??n);
        logger.info("Fin patchComunicado " + id);
        return comunicado;
    }

    //creo tambi??n un m??todo que capture la excepci??n y la devuelve un poco m??s elegante

    @ExceptionHandler(ComunicadoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ComunicadoNotFoundException comnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", comnfe.getMessage());
        logger.error(comnfe.getMessage(), comnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ProfesionalNotFoundException profnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", profnfe.getMessage());
        logger.error(profnfe.getMessage(), profnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepci??n gen??rica me sirve para controlar culquier excepci??n que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "1","Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
