package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.service.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/profesionales")
    public Profesional addProfesional (@RequestBody Profesional profesional){
        Profesional newProfesional = profesionalService.addProfesional(profesional);
        return newProfesional;
    }

    @GetMapping("/profesional/{id}")
    public Profesional getProfesional (@PathVariable long id){
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
    public Profesional removeProfesional (@PathVariable long id){
        Profesional profesional = profesionalService.removeProfesional(id);
        return profesional;
    }

    @PutMapping("/profesional/{id}")
    public Profesional modifyProfesional(@RequestBody Profesional profesional, @PathVariable long id){
        Profesional newProfesional = profesionalService.modifyProfesional(id, profesional);
        return newProfesional;
    }



}
