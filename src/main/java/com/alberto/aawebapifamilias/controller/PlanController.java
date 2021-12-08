package com.alberto.aawebapifamilias.controller;

import com.alberto.aawebapifamilias.domain.Plan;
import com.alberto.aawebapifamilias.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/planes")
    public Plan addPlan (@RequestBody Plan plan){
        Plan newPlan = planService.addPlan(plan);
        return newPlan;
    }

    @GetMapping("/plan/{id}")
    public Plan getPlan (@PathVariable long id){
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
    public Plan removePlan (@PathVariable long id){
        Plan plan = planService.removePlan(id);
        return plan;
    }

    @PutMapping("/plan/{id}")
    public Plan modifyPlan(@RequestBody Plan plan, @PathVariable long id){
        Plan newPlan = planService.modifyPlan(id, plan);
        return newPlan;
    }



}
