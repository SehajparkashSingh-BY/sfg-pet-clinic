package com.petclinic.controllers;

import com.petclinic.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.petclinic.services.OwnerService;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService){
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }
    @RequestMapping({"","/index","/index.html"})
    public String listOwners(Model model){

        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", new Owner());
        return "owners/findowners";
    }

    @GetMapping("/processLastName")
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if(results.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (results.size() == 1){
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        else{
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable String ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(Long.valueOf(ownerId)));
        return mav;
    }

    @GetMapping("/new")
    public String createOwner(Model model){
        model.addAttribute("owner", new Owner());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    //@Valid Owner owner
    public String saveOwner(@ModelAttribute Owner owner, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }
        else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+ savedOwner.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String editOwner(@PathVariable Long id, Model model){
        model.addAttribute("owner",ownerService.findById(id));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("{id}/edit")
    public String updateOwner(@PathVariable Long id, @ModelAttribute Owner owner, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }
        else {
            owner.setId(id);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }
}
