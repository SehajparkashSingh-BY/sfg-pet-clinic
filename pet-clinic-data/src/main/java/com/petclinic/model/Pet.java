package com.petclinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id")  //name of other foreign key column
    private PetType petType;

    @ManyToOne            //uni-directional
    @JoinColumn(name = "owner_id")  //name of other foreign key column
    private Owner owner;
    @Column(name = "bith_date")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public PetType getPetType() {
//        return petType;
//    }
//
//    public void setPetType(PetType petType) {
//        this.petType = petType;
//    }
//
//    public Owner getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthdate) {
//        this.birthDate = birthdate;
//    }
//
//    public Set<Visit> getVisits() {
//        return visits;
//    }
//
//    public void setVisits(Set<Visit> visits) {
//        this.visits = visits;
//    }
}
