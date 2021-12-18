package com.alberto.aawebapifamilias.service;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.domain.Profesional;
import com.alberto.aawebapifamilias.domain.Residente;
import com.alberto.aawebapifamilias.domain.dto.PlanDto;
import com.alberto.aawebapifamilias.exception.PlanNotFoundException;
import com.alberto.aawebapifamilias.exception.ProfesionalNotFoundException;
import com.alberto.aawebapifamilias.exception.ResidenteNotFoundException;
import com.alberto.aawebapifamilias.repository.PlanRepository;
import com.alberto.aawebapifamilias.repository.ProfesionalRepository;
import com.alberto.aawebapifamilias.repository.ResidenteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ResidenteRepository residenteRepository;
    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Override
    public Plan addPlan(PlanDto planDto) throws ResidenteNotFoundException, ProfesionalNotFoundException {
        //tengo que recuperar los objetos enteros para pasarlos a la base de datos no solo el id
        Residente residente = residenteRepository.findById(planDto.getResidente())
                .orElseThrow(ResidenteNotFoundException::new);
        Profesional profesional = profesionalRepository.findById(planDto.getProfesional())
                .orElseThrow(ProfesionalNotFoundException::new);
        /*
        vamos a utilizar un mapeador(introducir dependencia modelmapper) para no tener que ir indicandolo al
        nuevo objeto todos los atributos
        Le indico mapeame el objeto que te indico en el nuevo plan con lo que debe tener cualquier plan y ya cojes la
        información y yo le indico a mayores los objetos completos que no estan en el DTO, no solo los id.
        Con el map me ahorro los get set para poder crear un objeto. Solo mapea lo que es común a ambos objetos y
        lo demas lo ignora.
         */
        ModelMapper mapper = new ModelMapper();
        Plan plan = mapper.map(planDto, Plan.class);
        plan.setResidente(residente);
        plan.setProfesional(profesional);
        return planRepository.save(plan);
    }

    @Override
    public Plan findPlan(long id) throws PlanNotFoundException {
        return planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
    }

    @Override
    public List<Plan> findAllPlanes() {
        return planRepository.findAll();
    }

    @Override
    public List<Plan> findAllPlanesById(long id) {
        return planRepository.findAllPlanesById(id);
    }

    @Override
    public Plan removePlan(long id) throws PlanNotFoundException{
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        planRepository.delete(plan);
        return plan;
    }

    @Override
    public Plan modifyPlan(long id, PlanDto newPlan) throws PlanNotFoundException, ResidenteNotFoundException, ProfesionalNotFoundException {
        Plan plan = planRepository.findById(id).
                orElseThrow(PlanNotFoundException::new);
        Residente residente = residenteRepository.findById(newPlan.getResidente())
                .orElseThrow(ResidenteNotFoundException::new);
        Profesional profesional = profesionalRepository.findById(newPlan.getProfesional())
                .orElseThrow(ProfesionalNotFoundException::new);
        /*
         * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
         * para que no me cree un nuevo familiar y si realice la modificación sobre el familiar indicado.
         */
        ModelMapper mapper = new ModelMapper();
        plan = mapper.map(newPlan, Plan.class);
//        plan.setNombrePlan(newPlan.getNombrePlan());
//        plan.setTerapia(newPlan.getTerapia());
//        plan.setDescripcion(newPlan.getDescripcion());
//        plan.setFechaInicio(newPlan.getFechaInicio());
//        plan.setFechaFin(newPlan.getFechaFin());
//        plan.setImportante(newPlan.getImportante());
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> findPlanesByResidente(Residente residente) {
        return planRepository.findByResidente(residente);
    }
}
