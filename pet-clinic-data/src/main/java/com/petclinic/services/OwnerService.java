package com.petclinic.services;

import com.petclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudServices<Owner,Long>{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

//    Owner findById(Long id);
//
//    Owner save(Owner owner);
//
//    Set<Owner> findAll();
}
