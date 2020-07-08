package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.services.TopoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopoController {

    private final TopoRepo topoRepo;
    private final TopoService topoService;

    public TopoController(TopoRepo topoRepo, TopoService topoService) {
        this.topoRepo = topoRepo;
        this.topoService = topoService;
    }

    @GetMapping("/registertopo")
    public String getFormNewTopo(Model model){
        model.addAttribute("topo", new Topo());
        return "topos/register";
    }

    @PostMapping("/registertoposave")
    public String postFormNewTopo(@ModelAttribute Topo topo){
        topoService.addNewTopo(topo);
        return "topos/results";
    }

    @GetMapping("/registertopo/{id}")
    public String updateTopo(@PathVariable("id") Long currentTopoId, Model model) {
        model.addAttribute("currentTopo", topoRepo.findById(currentTopoId));
        return "topos/register";
    }

    @PostMapping("/registertoposave/{id}")
    public String postUpdateTopo(@PathVariable("id") Long currentTopoId, @ModelAttribute Topo topo){
        topoRepo.save(topo);
        return "topos/results";
    }
}
