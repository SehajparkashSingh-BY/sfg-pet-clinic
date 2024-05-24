package services;

import model.Owner;

public interface OwnerService extends CrudServices<Owner,Long>{
    Owner findByLastName(String lastName);

//    Owner findById(Long id);
//
//    Owner save(Owner owner);
//
//    Set<Owner> findAll();
}
