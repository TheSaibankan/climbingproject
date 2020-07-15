package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.Topo;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.TopoRepo;
import com.oc6ad.climbingproject.services.TopoService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopoController {

    private final TopoRepo topoRepo;
    private final TopoService topoService;
    private final UserAccountService userAccountService;

    public TopoController(TopoRepo topoRepo, TopoService topoService, UserAccountService userAccountService) {
        this.topoRepo = topoRepo;
        this.topoService = topoService;
        this.userAccountService = userAccountService;
    }

    @GetMapping("/alltopos")
    public String getAllTopos(Model model){
        model.addAttribute("topos", topoRepo.findAllByIsAvailableTrue());
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "topos/list";
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

    @GetMapping("/edittopo/{id}")
    public String updateTopo(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentTopo", topoRepo.findById(id).get());
        return "topos/update";
    }

    @PostMapping("/updatetopo/{id}")
    public String postUpdateTopo(@PathVariable("id") Long id, @Validated Topo topo, BindingResult result, Model model){
    if (result.hasErrors()) {
        topo.setId(id);
        return "topos/update";
    }
    topoService.addNewTopo(topo);
    return "topos/results";
    }

    @GetMapping("/deletetopo/{id}")
    public String deleteTopo(@PathVariable("id") long id, Model model) {
        topoRepo.delete(topoRepo.findById(id).get());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        model.addAttribute("currentTopos", topoService.getToposByCurrentUser());
        return "useraccounts/testLogin";
    }

    @GetMapping("/asktopo/{id}")
    public String askForTopo(@PathVariable("id") long id) {
        if (topoService.canBeRequested(id)){topoService.askForTopo(id);}
        return "topos/successRequestTopo";
    }

    @GetMapping("acceptrequest/{id}")
    public String acceptTopo(@PathVariable("id") long id, Model model) {
        topoService.acceptRequestTopo(id);
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        model.addAttribute("currentTopos", topoService.getToposByCurrentUser());
        model.addAttribute("currentRequester", topoService.getUserRequesting(id));
        return "useraccounts/testLogin";
    }

    @GetMapping("declinerequest/{id}")
    public String declineTopo(@PathVariable("id") long id, Model model) {
        topoService.declineRequestTopo(id);
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        model.addAttribute("currentTopos", topoService.getToposByCurrentUser());
        return "useraccounts/testLogin";
    }
}
