package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.exception.CentroNotFoundException;
import com.alberto.aawebapifamilias.exception.ErrorResponse;
import com.alberto.aawebapifamilias.service.CentroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CentroController {

    private final Logger logger = LoggerFactory.getLogger(CentroController.class);

    @Autowired
    private CentroService centroService;


    @PostMapping("/centros")
    public Centro addCentro (@RequestBody Centro centro) {
        logger.info("Inicio addCentro");
        Centro newCentro = centroService.addCentro(centro);
        logger.info("Fin addCentro");
        return newCentro;

    }

    @GetMapping("/centro/{nombre}")
    public Centro getCentro (@PathVariable String nombre) throws CentroNotFoundException {
        logger.info("Inicio getCentro{nombre}");
        Centro centro = centroService.findCentro(nombre);
        logger.info("Fin getCentro{nombre}");
        return centro;
    }

    @GetMapping("/centro/{id}")
    public Centro getCentro (@PathVariable long id) throws CentroNotFoundException {
        logger.info("Inicio getCentro{id}");
        Centro centro = centroService.findCentro(id);
        logger.info("Fin getCentro{id}");
        return centro;
    }

    @GetMapping("/centros")
    public List<Centro> getCentros(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "numRegistro", required = false) String numRegistro,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "all", defaultValue = "true") boolean all){
        logger.info("Inicio getCentros");
        List<Centro> centros;

        if(all){
            logger.info("Muestra todos los centros");
            centros = centroService.findAll();
        } else {
            logger.info("Muestra centros que cumplen los parametros");
            centros = centroService.findAllCentros(nombre, numRegistro, email);
        }
        logger.info("Fin getCentros");
        return centros;
    }

    @DeleteMapping("/centro/{id}")
    public Centro removeCentro(@PathVariable long id) throws CentroNotFoundException {
        logger.info("Inicio removeCentro");
        Centro centro = centroService.removeCentro(id);
        logger.info("Fin removeCentro");
        return centro;
    }

    @PutMapping("/centro/{id}")
    public Centro modifyCentro(@RequestBody Centro centro, @PathVariable long id)
            throws CentroNotFoundException{
        logger.info("Inicio modifyCentro");
        Centro newCentro = centroService.modifyCentro(id, centro);
        logger.info("Fin modifyCentro");
        return newCentro;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(CentroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCentroNotFoundException(CentroNotFoundException cnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
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
