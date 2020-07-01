package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserAccountController {

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping("/all")
    public String getUserAccounts(Model model) {
        model.addAttribute("useraccounts", userAccountService.findAll());
         return "useraccounts/list";
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


}
