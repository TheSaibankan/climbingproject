package com.oc6ad.climbingproject.controllers;

import com.oc6ad.climbingproject.model.ClimbingSpot;
import com.oc6ad.climbingproject.model.Comment;
import com.oc6ad.climbingproject.model.Sector;
import com.oc6ad.climbingproject.repositories.CommentRepo;
import com.oc6ad.climbingproject.repositories.RouteRepo;
import com.oc6ad.climbingproject.repositories.SectorRepo;
import com.oc6ad.climbingproject.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClimbingSpotController {

    private final ClimbingSpotService climbingSpotService;
    private final UserAccountService userAccountService;
    private final CommentService commentService;
    private final SectorService sectorService;
    private final RouteService routeService;

    public ClimbingSpotController(ClimbingSpotService climbingSpotService, UserAccountService userAccountService, CommentRepo commentRepo, CommentService commentService, SectorService sectorService, SectorRepo sectorRepo, RouteRepo routeRepo, RouteService routeService) {
        this.climbingSpotService = climbingSpotService;
        this.userAccountService = userAccountService;
        this.commentService = commentService;
        this.sectorService = sectorService;
        this.routeService = routeService;
    }

    @GetMapping("/climbingspot/{id}")
    public String GetSpot(@PathVariable("id") Long id, Model model){
        ClimbingSpot climbingSpot = climbingSpotService.findById(id).get();
        model.addAttribute("currentSpot", climbingSpot);
        model.addAttribute("currentSectors", climbingSpot.getSectors());
        model.addAttribute("currentRoutes", routeService.findAllBySectorClimbingSpotId(id));
        model.addAttribute("comments", climbingSpot.getComments());
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

    @GetMapping("/addnewsector/{id}")
    public String getFormNewSector(@PathVariable("id") Long id, Model model){
        model.addAttribute("sector", new Sector());
        model.addAttribute("currentSpot", climbingSpotService.findById(id).get());
        return "climbingsites/registerSector";
    }

    @PostMapping("/registersectorsave/{id}")
    public String postFormNewSector(@ModelAttribute Sector sector, @PathVariable("id") Long id, Model model){
        ClimbingSpot currentSpot = climbingSpotService.findById(id).get();
        model.addAttribute("spotId", currentSpot.getId());
        sectorService.addNewSector(id, sector);
        return "climbingsites/resultsUpdateSpot";
    }

    @PostMapping("/registerspotsave")
    public String postFormNewTopo(@ModelAttribute ClimbingSpot spot){
        climbingSpotService.addNewSpot(spot);
        return "climbingsites/results";
    }

    @PostMapping("/registernewcomment/{id}")
    public String postNewComment(@ModelAttribute Comment comment, @PathVariable("id") Long id, Model model){
        commentService.addNewComment(id, comment);
        ClimbingSpot climbingSpot = climbingSpotService.findById(id).get();
        model.addAttribute("currentSectors", climbingSpot.getSectors());
        model.addAttribute("currentRoutes", routeService.findAllBySectorClimbingSpotId(id));
        model.addAttribute("currentSpot", climbingSpot);
        model.addAttribute("comments", climbingSpot.getComments());
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        model.addAttribute("comment", new Comment());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "climbingsites/climbingspot";
    }

    @GetMapping("/editspot/{id}")
    public String updateSpot(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentSpot", climbingSpotService.findById(id).get());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "climbingsites/update";
    }

    @GetMapping("/editsector/{id}")
    public String updateSector(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentSector", sectorService.findById(id).get());
        return "climbingsites/updateSector";
    }

    @PostMapping("/updatespot/{id}")
    public String postUpdateTopo(@PathVariable("id") Long id, @Validated ClimbingSpot climbingSpot, BindingResult result, Model model){
        if (result.hasErrors()) {
            climbingSpot.setId(id);
            return "climbingsites/update";
        }
        climbingSpotService.addNewSpot(climbingSpot);
        return "climbingsites/results";
    }

    @PostMapping("/updatesector/{id}")
    public String postUpdateSector(@PathVariable("id") Long id, @Validated Sector sector, BindingResult result, Model model){
        if (result.hasErrors()){
            sector.setId(id);
            return "climbingsites/updateSector";
        }
        sectorService.save(sector);
        model.addAttribute("spotId", sectorService.findById(id).get().getClimbingSpot().getId());
        return "climbingsites/resultsUpdateSpot";
    }

    @GetMapping("/deletespot/{id}")
    public String getHome(@PathVariable("id") Long id, Model model){
        climbingSpotService.deleteById(id);
        model.addAttribute("climbingspots", climbingSpotService.findAll());
        model.addAttribute("isConnected", userAccountService.isUserConnected());
        model.addAttribute("currentUser", userAccountService.getCurrentUserAccount());
        return "index";
    }

    @GetMapping("/deletesector/{id}")
    public String getDeleteSector(@PathVariable("id") Long id, Model model){
        model.addAttribute("spotId", sectorService.findById(id).get().getClimbingSpot().getId());
        sectorService.deleteById(id);
        return "climbingsites/resultsUpdateSpot";
    }

    @GetMapping("deletecomment/{idComment}")
    public String deleteComment(@PathVariable("idComment") Long idComment, Model model){
        model.addAttribute("spotId", commentService.findById(idComment).get().getClimbingSpot().getId());
        commentService.deleteById(idComment);
        return "climbingsites/commentDeleted";
    }
}
