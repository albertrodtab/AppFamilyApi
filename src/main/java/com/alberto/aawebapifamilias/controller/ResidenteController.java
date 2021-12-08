package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.service.ResidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResidenteController {

    @Autowired
    private ResidenteService residenteService;

    @PostMapping("/residentes")
    public Residente addResidente (@RequestBody Residente residente){
        Residente newResidente = residenteService.addResidente(residente);
        return newResidente;
    }

    @GetMapping("/residente/{id}")
    public Residente getFamiliar (@PathVariable long id){
        Residente residente = residenteService.findResidente(id);
        return residente;
    }

    @GetMapping("/residentes")
    public List<Residente> getResidenteById (@RequestParam(name = "residente", defaultValue = "0") long id){
        List<Residente> residentes;

        if(id == 0){
            residentes = residenteService.findAllResidentes();
        } else {
            residentes = residenteService.findAllResidentes(id);
        }
        return residentes;
    }

    @DeleteMapping("/residente/{id}")
    public Residente removeResidente (@PathVariable long id){
        Residente residente = residenteService.removeResidente(id);
        return residente;
    }

    @PutMapping("/residente/{id}")
    public Residente modifyResidente(@RequestBody Residente residente, @PathVariable long id){
        Residente newResidente = residenteService.modifyResidente(id, residente);
        return newResidente;
    }



}
