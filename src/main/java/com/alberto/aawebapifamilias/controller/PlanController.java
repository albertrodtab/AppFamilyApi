package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.dto.PlanDto;
import com.alberto.aawebapifamilias.exception.*;
import com.alberto.aawebapifamilias.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/planes")
    public Plan addPlan (@RequestBody PlanDto planDto) throws ProfesionalNotFoundException, ResidenteNotFoundException {
        Plan newPlan = planService.addPlan(planDto);
        return newPlan;
    }

    @GetMapping("/plan/{id}")
    public Plan getPlan (@PathVariable long id) throws PlanNotFoundException{
        Plan plan = planService.findPlan(id);
        return plan;
    }

    @GetMapping("/planes")
    public List<Plan> getPlanById (@RequestParam(name = "plan", defaultValue = "0") long id){
        List<Plan> planes;

        if(id == 0){
            planes = planService.findAllPlanes();
        } else {
            planes = planService.findAllPlanesById(id);
        }
        return planes;
    }

    @DeleteMapping("/plan/{id}")
    public Plan removePlan (@PathVariable long id) throws PlanNotFoundException{
        Plan plan = planService.removePlan(id);
        return plan;
    }

    @PutMapping("/plan/{id}")
    public Plan modifyPlan(@RequestBody PlanDto planDto, @PathVariable long id) throws PlanNotFoundException, ProfesionalNotFoundException, ResidenteNotFoundException {
        Plan newPlan = planService.modifyPlan(id, planDto);
        return newPlan;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlanNotFoundException(PlanNotFoundException pnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "1","Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
