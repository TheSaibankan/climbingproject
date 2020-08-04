package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import com.oc6ad.climbingproject.services.TopoService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This controller deals with general mapping, such as the home page
 */
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

        if (Strings.isNotBlank(search)){
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
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        return "useraccounts/register";
    }

    @PostMapping("/registersave")
    public String postFormNewUserAccount(@ModelAttribute UserAccount userAccount, Model model){
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        if (userAccountService.existsByLogin(userAccount.getLogin()) == true){
            model.addAttribute("userAlreadyExists", true);
            model.addAttribute("userAccount", new UserAccount());
            return "useraccounts/register";
        }
        userAccountService.addUserAccount(userAccount);
        return "useraccounts/results";
    }

    @RequestMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        return "useraccounts/login";
    }


    @RequestMapping("/logoutsuccess")
    public String getLogout(Model model){
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        return "/";
    }

    @RequestMapping("/userinterface")
    public String getUserSpace(Model model) {
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        model.addAttribute("currentTopos", topoService.getToposByCurrentUser());
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        return "useraccounts/testLogin";
    }


}
