package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.exception.ErrorResponse;
import com.alberto.aawebapifamilias.exception.FamiliarNotFoundException;
import com.alberto.aawebapifamilias.service.FamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamiliarController {

    @Autowired
    private FamiliarService familiarService;

    @PostMapping("/familiares")
    public Familiar addFamiliar(@RequestBody Familiar familiar){
        Familiar newFamiliar = familiarService.addFamiliar(familiar);
        return newFamiliar;
    }

    @GetMapping("/familiar/{id}")
    public Familiar getFamiliar (@PathVariable long id) throws FamiliarNotFoundException {
        Familiar familiar = familiarService.findFamiliar(id);
        return familiar;
    }

    @GetMapping("/familiares")
    public List<Familiar> getFamiliaresById (@RequestParam(name = "familiar", defaultValue = "0") long id) throws FamiliarNotFoundException{
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
        Familiar familiar = familiarService.removeFamiliar(id);
        return familiar;
    }

    @PutMapping("/familiar/{id}")
    public Familiar modifyFamiliar(@RequestBody Familiar familiar, @PathVariable long id) throws FamiliarNotFoundException{
        Familiar newFamiliar = familiarService.modifyFamiliar(id, familiar);
        return newFamiliar;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(FamiliarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFamiliarNotFoundException(FamiliarNotFoundException fnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", fnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
