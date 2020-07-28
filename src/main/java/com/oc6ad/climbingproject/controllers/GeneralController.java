package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import com.oc6ad.climbingproject.services.TopoService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GeneralController {

    private final UserAccountService userAccountService;
    private final TopoService topoService;
    private final ClimbingSpotService climbingSpotService;

    public GeneralController(UserAccountService userAccountService, TopoService topoService, ClimbingSpotService climbingSpotService) {
        this.userAccountService = userAccountService;
        this.topoService = topoService;
        this.climbingSpotService = climbingSpotService;
    }

    @GetMapping("/")
    public String getHome(Model model, String search){

        if (search != null){
            model.addAttribute("climbingspots", climbingSpotService.findBySearch(search));
        } else {
            model.addAttribute("climbingspots", climbingSpotService.findAllSpots());
        }

        model.addAttribute("isConnected", userAccountService.isUserConnected());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "index";
    }

    @GetMapping("/register")
    public String getFormNewUserAccount(Model model){
        model.addAttribute("userAccount", new UserAccount());
        return "useraccounts/register";
    }

    @PostMapping("/registersave")
    public String postFormNewUserAccount(@ModelAttribute UserAccount userAccount){
        userAccountService.addUserAccount(userAccount);
        return "useraccounts/results";
    }

    @RequestMapping("/login")
    public String getLogin(){
        return "useraccounts/login";
    }


    @RequestMapping("/logoutsuccess")
    public String getLogout(){
        return "/";
    }

    @RequestMapping("/userinterface")
    public String getUserSpace(Model model) {
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        model.addAttribute("currentTopos", topoService.getToposByCurrentUser());
        return "useraccounts/testLogin";
    }


}
