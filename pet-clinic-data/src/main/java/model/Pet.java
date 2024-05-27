package model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id")  //name of other foreign key column
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")  //name of other foreign key column
    private Owner owner;
    @Column(name = "bith_date")
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthdate() {
        return birthDate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthDate = birthdate;
    }
}
