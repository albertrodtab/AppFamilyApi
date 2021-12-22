package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.ParticipaDTO;
import com.alberto.aawebapifamilias.domain.dto.PlanDto;
import com.alberto.aawebapifamilias.domain.dto.RelacionDTO;
import com.alberto.aawebapifamilias.exception.*;
import com.alberto.aawebapifamilias.service.PlanService;
import com.alberto.aawebapifamilias.service.ProfesionalService;
import com.alberto.aawebapifamilias.service.ResidenteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PlanController {

    private final Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;
    @Autowired
    private ResidenteService residenteService;
    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/planes")
    public Plan addPlan (@RequestBody PlanDto planDto) throws ProfesionalNotFoundException {
        logger.info("Inicio addFamiliar");
        Plan newPlan = planService.addPlan(planDto);
        logger.info("Fin addFamiliar");
        return newPlan;
    }

    /*
     * Relacionar un familiar con un residente
     * */
    @PostMapping("/participa")
    public ResponseEntity<Response> participa(@RequestBody ParticipaDTO participaDTO)
            throws PlanNotFoundException, ResidenteNotFoundException {
        logger.info("Inicio participa");
        Residente residente = residenteService.findResidente(participaDTO.getResidenteId());
        logger.info("Residente encontrado " + residente.getId());
        Plan plan = planService.findPlan(participaDTO.getPlanId());
        logger.info("Plan encontrado " + plan.getId());
        planService.addParticipa(residente, plan);


        Response response = new Response("1", "Residente añadido al Plan " +
                participaDTO.getPlanId());
        logger.info("Fin participa");
        return new ResponseEntity<>(response, HttpStatus.OK);
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
        logger.info("Inicio removeFamiliar");
        Plan plan = planService.removePlan(id);
        logger.info("Fin removeFamiliar");
        return plan;
    }

    @PutMapping("/plan/{id}")
    public Plan modifyPlan(@RequestBody Plan plan, @PathVariable long id) throws PlanNotFoundException, ProfesionalNotFoundException {
        logger.info("Inicio modigyFamiliar");
        Plan newPlan = planService.modifyPlan(id, plan);
        logger.info("Fin modifyFamiliar");
        return newPlan;
    }

    // Cambiar la importancia de un plan
    @PatchMapping("/plan/{id}")
    public Plan patchPlan (@PathVariable long id, @RequestBody boolean importante) throws PlanNotFoundException {
        logger.info("Inicio PatchPlan " + id);
        Plan plan = planService.patchPlan(id, importante);
        logger.info("Fin patchPlan " + id);
        return plan;
    }

    // Contar los residentes totales de un plan. SQL
    @GetMapping("/plan/{id}/numResidentes")
    public int numResidentesPlan(@PathVariable long id) throws PlanNotFoundException{
        logger.info("Inicio numResidentesPlan " + id);
        int residentes = planService.numResidentes(id);
        logger.info("rin numResidentesPlan " + id);
        return residentes;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlanNotFoundException(PlanNotFoundException pnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProfesionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ProfesionalNotFoundException profnfe){
        ErrorResponse errorResponse = new ErrorResponse("404", profnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ErrorResponse> handleResidenteNotFoundException(ResidenteNotFoundException rnfe){
        ErrorResponse errorResponse = new ErrorResponse("1", rnfe.getMessage());
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
