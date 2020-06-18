package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import org.apache.catalina.User;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserAccountController {

    private final UserAccountRepo userAccountRepo;

    public UserAccountController(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }


    @RequestMapping("/all")
    public String getUserAccounts(Model model) {

        model.addAttribute("useraccounts", userAccountRepo.findAll());

         return "useraccounts/list";
    }
    @GetMapping("/register")
    public String getFormNewUserAccount(Model model){
        model.addAttribute("userAccount", new UserAccount());
        return "useraccounts/register";
    }
    @GetMapping("/register/{id}")
    public String getFormUserAccount(Model model, @PathVariable Long id){
        model.addAttribute("userAccount",userAccountRepo.findById(id));
        return "useraccounts/register";
    }

    @PostMapping("/register")
    public String submitNewUserAccount(@ModelAttribute UserAccount userAccount){
        UserAccount saveUser = userAccountRepo.save(userAccount);
        return "redirect:/users/register/"+saveUser.getId();
    }

}
