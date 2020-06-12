package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.repositories.ClimbingSiteRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClimbingSiteController {

    private final ClimbingSiteRepo climbingSiteRepo;

    public ClimbingSiteController(ClimbingSiteRepo climbingSiteRepo) {
        this.climbingSiteRepo = climbingSiteRepo;
    }

    @RequestMapping("/climbingsites")
    public String getClimbingSites(Model model){
        model.addAttribute("climbingsites", climbingSiteRepo.findAll());

        return "climbingsites/list";
    }

}
