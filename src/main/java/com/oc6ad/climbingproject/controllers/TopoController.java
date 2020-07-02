package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.repositories.TopoRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopoController {

    private final TopoRepo topoRepo;

    public TopoController(TopoRepo topoRepo) {
        this.topoRepo = topoRepo;
    }

    @RequestMapping("/alltopos")
    public  String getTopos(Model model) {
        model.addAttribute("topos", topoRepo.findAll());

        return "topos/list";
    }
}
