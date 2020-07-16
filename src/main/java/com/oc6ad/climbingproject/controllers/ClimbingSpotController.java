package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClimbingSpotController {

    private final ClimbingSpotRepo climbingSpotRepo;
    private final ClimbingSpotService climbingSpotService;

    public ClimbingSpotController(ClimbingSpotRepo climbingSpotRepo, ClimbingSpotService climbingSpotService) {
        this.climbingSpotRepo = climbingSpotRepo;
        this.climbingSpotService = climbingSpotService;
    }

    @GetMapping("/climbingspot/{id}")
    public String GetSpot(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentSpot", climbingSpotRepo.findById(id).get());
        return "climbingsites/climbingspot";
    }

    @GetMapping("/registerspot")
    public String getFormNewSpot(Model model){
        model.addAttribute("spot", new ClimbingSpot());
        return "climbingsites/register";
    }

    @PostMapping("/registerspotsave")
    public String postFormNewTopo(@ModelAttribute ClimbingSpot spot){
        climbingSpotService.addNewSpot(spot);
        return "climbingsites/results";
    }



}
