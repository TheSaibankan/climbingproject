package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountController {

    private final UserAccountRepo userAccountRepo;

    public UserAccountController(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    @RequestMapping("/useraccounts")
    public String getUserAccounts(Model model) {

        model.addAttribute("useraccounts", userAccountRepo.findAll());

         return "useraccounts/list";
    }
}
