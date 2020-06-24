package se.lexicon.petclinic.service;

import se.lexicon.petclinic.entity.Pet;

import java.util.List;

public interface PetService {

    List<Pet> findAll();
    Pet findById(String id);
    Pet findByName(String name);

    Pet save(Pet pet);
    Pet update(Pet pet);

    void delete(Pet pet);
}
