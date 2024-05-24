package com.petclinicweb.bootstrap;

import model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import services.OwnerService;
import services.PetTypeService;
import services.SpecialityService;
import services.VetService;
import services.map.OwnerServiceMap;
import services.map.VetServiceMap;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetServiceMap vetService, PetTypeService petTypeService, SpecialityService specialityService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Owner owner1 = new Owner();
        //owner1.setId(1L);
        owner1.setFirstName("Sidak");
        owner1.setLastName("Singh");
        owner1.setAddress("123 Devarabisnahalli");
        owner1.setCity("Bengaluru");
        owner1.setTelephone("2134567");

        Pet sidaksPet = new Pet();
        sidaksPet.setPetType(savedDogPetType);
        sidaksPet.setOwner(owner1);
        sidaksPet.setName("Rosco");
        sidaksPet.setBirthdate(LocalDate.now());

        owner1.getPets().add(sidaksPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        //owner2.setId(2L);
        owner2.setFirstName("Nithin");
        owner2.setLastName("Balaji");
        owner2.setAddress("124 Devarabisnahalli");
        owner2.setCity("Bengaluru");
        owner2.setTelephone("2134567");

        Pet nithinsPet = new Pet();
        nithinsPet.setPetType(savedCatPetType);
        nithinsPet.setOwner(owner2);
        nithinsPet.setName("Just cat");
        nithinsPet.setBirthdate(LocalDate.now());

        owner2.getPets().add(nithinsPet);
        ownerService.save(owner2);

        System.out.println("Loading Owners.......");

        Vet vet1 = new Vet();
        //vet1.setId(1L);
        vet1.setFirstName("German");
        vet1.setLastName("Shepherd");

        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        //vet2.setId(2L);
        vet2.setFirstName("Husky");
        vet2.setLastName("Dog");

        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loading Vets.......");
    }
}
