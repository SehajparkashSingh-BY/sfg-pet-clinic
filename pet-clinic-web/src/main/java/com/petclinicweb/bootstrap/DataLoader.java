package com.petclinicweb.bootstrap;

import model.Owner;
import model.Vet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import services.OwnerService;
import services.VetService;
import services.map.OwnerServiceMap;
import services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetServiceMap vetService){
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Sidak");
        owner1.setLastName("Singh");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Nithin");
        owner2.setLastName("Balaji");
        ownerService.save(owner2);

        System.out.println("Loading Owners.......");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("German");
        vet1.setLastName("Shepherd");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Husky");
        vet2.setLastName("Dog");
        vetService.save(vet2);

        System.out.println("Loading Vets.......");
    }
}
