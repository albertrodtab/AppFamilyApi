package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.domain.dto.CentroDto;
import com.alberto.aawebapifamilias.exception.CentroNotFoundException;
import com.alberto.aawebapifamilias.exception.ErrorResponse;
import com.alberto.aawebapifamilias.exception.ResidenteNotFoundException;
import com.alberto.aawebapifamilias.service.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CentroController {

    @Autowired
    private CentroService centroService;


    @PostMapping("/centros")
    public Centro addCentro (@RequestBody Centro centro) {
        Centro newCentro = centroService.addCentro(centro);
        return newCentro;

    }

    @GetMapping("/centro/{id}")
    public Centro getCentro (@PathVariable long id) throws CentroNotFoundException {
        Centro centro = centroService.findCentro(id);
        return centro;
    }

    @GetMapping("/centros")
    public List<Centro> getCentroById(@RequestParam(name = "centro", defaultValue = "0") long id){
        List<Centro> centros;

        if(id == 0){
            centros = centroService.findAll();
        } else {
            centros = centroService.findAllCentros(id);
        }
        return centros;
    }

    @DeleteMapping("/centro/{id}")
    public Centro removeCentro(@PathVariable long id) throws CentroNotFoundException {
        Centro centro = centroService.removeCentro(id);
        return centro;
    }

    @PutMapping("/centro/{id}")
    public Centro modifyCentro(@RequestBody Centro centro, @PathVariable long id)
            throws CentroNotFoundException{
        Centro newCentro = centroService.modifyCentro(id, centro);
        return newCentro;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(CentroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCentroNotFoundException(CentroNotFoundException cnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "1","Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
