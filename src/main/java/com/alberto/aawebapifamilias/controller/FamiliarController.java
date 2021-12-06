package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.service.FamiliarService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Familiar getFamiliar (@PathVariable long id){
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
    public Familiar removeFamiliar (@PathVariable long id){
        Familiar familiar = familiarService.removeFamiliar(id);
        return familiar;
    }

    @PutMapping("/familiar/{id}")
    public Familiar modyfiFamiliar (@RequestBody Familiar familiar, @PathVariable long id){
        Familiar newFamiliar = familiarService.modifyFamiliar(id, familiar);
        return newFamiliar;
    }



}
