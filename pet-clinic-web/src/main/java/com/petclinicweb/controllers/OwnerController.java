package com.petclinicweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.OwnerService;
import services.map.OwnerServiceMap;

@RequestMapping("owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerServiceMap ownerService){
        this.ownerService = ownerService;
    }
    @RequestMapping({"","/index","/index.html"})
    public String index(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
