package se.lexicon.petclinic.service;

import se.lexicon.petclinic.entity.Owner;

import java.util.List;

public interface OwnerService {

    Owner findById(String ownerId);
    List<Owner> findAll();
    Owner findByTelephone(String telephone);

    Owner create(Owner owner);
    Owner update(Owner owner);

    void delete(Owner owner);

}
