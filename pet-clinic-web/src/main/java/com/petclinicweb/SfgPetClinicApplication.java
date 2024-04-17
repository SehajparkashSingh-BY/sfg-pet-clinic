package com.petclinicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@ComponentScan(basePackages = {"sfg-pet-clinic"})
@SpringBootApplication(scanBasePackages = {"services","com.petclinicweb.bootstrap","com.petclinicweb.controllers"})
public class SfgPetClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SfgPetClinicApplication.class, args);
	}

}
