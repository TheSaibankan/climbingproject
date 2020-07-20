package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Comment;
import com.oc6ad.climbingproject.repositories.ClimbingSpotRepo;
import com.oc6ad.climbingproject.repositories.CommentRepo;
import com.oc6ad.climbingproject.services.ClimbingSpotService;
import com.oc6ad.climbingproject.services.CommentService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClimbingSpotController {

    private final ClimbingSpotRepo climbingSpotRepo;
    private final ClimbingSpotService climbingSpotService;
    private final UserAccountService userAccountService;
    private final CommentRepo commentRepo;
    private final CommentService commentService;

    public ClimbingSpotController(ClimbingSpotRepo climbingSpotRepo, ClimbingSpotService climbingSpotService, UserAccountService userAccountService, CommentRepo commentRepo, CommentService commentService) {
        this.climbingSpotRepo = climbingSpotRepo;
        this.climbingSpotService = climbingSpotService;
        this.userAccountService = userAccountService;
        this.commentRepo = commentRepo;
        this.commentService = commentService;
    }

    @GetMapping("/climbingspot/{id}")
    public String GetSpot(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentSpot", climbingSpotRepo.findById(id).get());
        model.addAttribute("comments", commentRepo.findAllByClimbingSpot_Id(id));
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        model.addAttribute("comment", new Comment());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "climbingsites/climbingspot";
    }

    @GetMapping("/registerspot")
    public String getFormNewSpot(Model model){
        model.addAttribute("spot", new ClimbingSpot());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "climbingsites/register";
    }

    @PostMapping("/registerspotsave")
    public String postFormNewTopo(@ModelAttribute ClimbingSpot spot){
        climbingSpotService.addNewSpot(spot);
        return "climbingsites/results";
    }

    @PostMapping("/registernewcomment/{id}")
    public String postNewComment(@ModelAttribute Comment comment, @PathVariable("id") Long id, Model model){
        commentService.addNewComment(id, comment);
        model.addAttribute("currentSpot", climbingSpotRepo.findById(id).get());
        model.addAttribute("comments", commentRepo.findAllByClimbingSpot_Id(id));
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        model.addAttribute("comment", new Comment());
        return "climbingsites/climbingspot";
    }

    @GetMapping("deletecomment/{idComment}")
    public String deleteComment(@PathVariable("idComment") Long idComment, Model model){
        model.addAttribute("idSpot", commentRepo.findById(idComment).get().getClimbingSpot().getId());
        commentRepo.deleteById(idComment);
        return "climbingsites/commentDeleted";
    }



}
