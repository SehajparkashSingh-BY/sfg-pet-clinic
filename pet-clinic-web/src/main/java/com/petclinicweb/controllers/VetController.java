package com.petclinicweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.VetService;
import services.map.VetServiceMap;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetServiceMap vetService){
        this.vetService = vetService;
    }
    @RequestMapping({"/vets","/vets/index","/vets/index.html", "vets.html"})
    public String index(Model model){

        model.addAttribute("vets",vetService.findAll());

        return "vets/index";
    }
}
