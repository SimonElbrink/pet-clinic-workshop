package se.lexicon.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.petclinic.entity.Pet;
import se.lexicon.petclinic.exceptions.ResourceNotFoundException;
import se.lexicon.petclinic.repository.PetRepository;

import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    PetRepository repo;

    @Autowired
    public PetServiceImpl(PetRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Pet> findAll() {

        List<Pet> foundItems = repo.findAll();

        if (foundItems.isEmpty()) throw new ResourceNotFoundException("Could not find any Pets.");

        return foundItems;

    }

    @Override
    public Pet findById(String id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find Resource with provided ID: " + id));

    }

    @Override
    public Pet findByName(String name) {
        return repo.findByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException("Could not find Resource with provided Name: " + name));
    }

    @Override
    @Transactional
    public Pet save(Pet pet) {
        return repo.save(pet);
    }

    @Override
    @Transactional
    public Pet update(Pet pet) {

     Pet foundItem = findById(pet.getPetId());

        foundItem.setName(pet.getName());
        foundItem.setBirthDate(pet.getBirthDate());
        foundItem.setOwner(pet.getOwner());
        foundItem.setPetType(pet.getPetType());

        return foundItem;
    }

    @Override
    public void delete(Pet pet) {
        repo.delete(pet);
    }
}
