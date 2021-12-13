package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Centro;
import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.service.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CentroController {

    @Autowired
    private CentroService centroService;


    @PostMapping("/centros")
    public Centro addCentro (@RequestBody Centro centro){
        Centro newCentro = centroService.addCentro(centro);
        return newCentro;

    }

    @GetMapping("/centro/{id}")
    public Centro getCentro (@PathVariable long id){
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
    public Centro removeCentro(@PathVariable long id){
        Centro centro = centroService.removeCentro(id);
        return centro;
    }

    @PutMapping("/centro/{id}")
    public Centro modifyCentro(@RequestBody Centro centro, @PathVariable long id){
        Centro newCentro = centroService.modifyCentro(id, centro);
        return newCentro;
    }



}
