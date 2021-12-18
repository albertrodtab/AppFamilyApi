package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Familiar;
import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.RelacionDTO;
import com.alberto.aawebapifamilias.domain.dto.ResidenteDto;
import com.alberto.aawebapifamilias.exception.*;
import com.alberto.aawebapifamilias.service.CentroService;
import com.alberto.aawebapifamilias.service.FamiliarService;
import com.alberto.aawebapifamilias.service.PlanService;
import com.alberto.aawebapifamilias.service.ResidenteService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResidenteController {

    private final Logger logger = LoggerFactory.getLogger(ResidenteController.class);

    @Autowired
    private ResidenteService residenteService;
    @Autowired
    private FamiliarService familiarService;
    @Autowired
    private PlanService planService;
    @Autowired
    private CentroService centroService;

    @PostMapping("/residentes")
    public Residente addResidente (@RequestBody ResidenteDto residenteDto) throws CentroNotFoundException {
        logger.info("Inicio addResidente");
        Residente newResidente = residenteService.addResidente(residenteDto);
        logger.info("Fin addResidente");
        return newResidente;
    }

    @GetMapping("/residente/{id}")
    public Residente getResidente (@PathVariable long id) throws ResidenteNotFoundException {
        Residente residente = residenteService.findResidente(id);
        return residente;
    }

    @GetMapping("/residente/{id}/planes")
    public List<Plan> getPlanesByResidente (@PathVariable long id) throws ResidenteNotFoundException{
        logger.info("Inicio getPlanesByResidente");
        List<Plan> planes = null;
        logger.info("Buscar el residente: " + id);
        Residente residente = residenteService.findResidente(id);
        logger.info("Residente encontrado: " + residente.getId());
        planes = planService.findPlanesByResidente(residente);
        logger.info("Planes del residente: " + planes.contains(residente));
        logger.info("Fin getPlanesByResidente");
        return planes;
    }

    @GetMapping("/residentes")
    public List<Residente> getResidenteById
            (@RequestParam(name = "residente", defaultValue = "0") long id){
        List<Residente> residentes;

        if(id == 0){
            residentes = residenteService.findAllResidentes();
        } else {
            residentes = residenteService.findAllResidentes(id);
        }
        return residentes;
    }

    @DeleteMapping("/residente/{id}")
    public Residente removeResidente (@PathVariable long id) throws ResidenteNotFoundException{
        logger.info("Inicio removeResidente");
        Residente residente = residenteService.removeResidente(id);
        logger.info("Fin removeResidente");
        return residente;
    }

    @PutMapping("/residente/{id}")
    public Residente modifyResidente(@RequestBody Residente residente, @PathVariable long id) throws ResidenteNotFoundException{
        logger.info("Inicio modifyResidente");
        Residente newResidente = residenteService.modifyResidente(id, residente);
        logger.info("Fin modifyResidente");
        return newResidente;
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante
    @ExceptionHandler(ResidenteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResidenteNotFoundException(ResidenteNotFoundException rnfe){
        ErrorResponse errorResponse = new ErrorResponse("1", rnfe.getMessage());
        //cuando devuelva una excepcion anoto un logger de error y cuando salte una excepción capturo la traza y la lanzo.
        //tambien puedo pasar la excepción completa.
        logger.error(rnfe.getMessage(), rnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Esta excepción genérica me sirve para controlar culquier excepción que yo no haya pensado y controlado.
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){
        ErrorResponse errorResponse = new ErrorResponse( "999","Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    * Relacionar un familiar con un usuario
    * */
    @PostMapping("/relacion")
    public ResponseEntity<Response> relacion(@RequestBody RelacionDTO relacionDTO)
            throws ResidenteNotFoundException, FamiliarNotFoundException {
        logger.info("Inicio relacion");
        Residente residente = residenteService.findResidente(relacionDTO.getResidenteId());
        logger.info("Residente encontrado " + residente.getId());
        Familiar familiar = familiarService.findFamiliar(relacionDTO.getFamiliarId());
        logger.info("Familiar encontrado " + familiar.getId());
        residenteService.addRelacion(residente, familiar);


        Response response = new Response("1", "Residente añadido al familiar " +
                relacionDTO.getFamiliarId());
        logger.info("Fin relacion");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
