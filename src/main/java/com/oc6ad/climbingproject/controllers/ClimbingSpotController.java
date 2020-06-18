package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/climbingspots")
public class ClimbingSpotController {

    private final ClimbingSpotRepo climbingSpotRepo;

    public ClimbingSpotController(ClimbingSpotRepo climbingSpotRepo) {
        this.climbingSpotRepo = climbingSpotRepo;
    }

    @RequestMapping("/all")
    public String getClimbingSites(Model model){
        model.addAttribute("climbingsites", climbingSpotRepo.findAll());

        return "climbingsites/list";
    }

}
