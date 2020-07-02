package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.services.UserAccountService;
import com.oc6ad.climbingproject.services.impl.UserPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GeneralController {

    private final UserAccountService userAccountService;
    private final ClimbingSpotRepo climbingSpotRepo;

    public GeneralController( UserAccountService userAccountService, ClimbingSpotRepo climbingSpotRepo) {
        this.userAccountService = userAccountService;
        this.climbingSpotRepo = climbingSpotRepo;
    }

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("climbingspots", climbingSpotRepo.findAll());
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
        return "useraccounts/testLogin";
    }


}