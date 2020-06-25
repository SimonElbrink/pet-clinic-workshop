package se.lexicon.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.petclinic.entity.Owner;
import se.lexicon.petclinic.entity.Pet;
import se.lexicon.petclinic.entity.PetType;
import se.lexicon.petclinic.exceptions.ResourceNotFoundException;
import se.lexicon.petclinic.repository.OwnerRepository;
import se.lexicon.petclinic.repository.PetRepository;
import se.lexicon.petclinic.repository.PetTypeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    PetRepository petRepo;
    OwnerRepository ownerRepo;
    PetTypeRepository petTypeRepo;

    @Autowired
    public PetServiceImpl(PetRepository petRepo, OwnerRepository ownerRepo, PetTypeRepository petTypeRepo) {
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
        this.petTypeRepo = petTypeRepo;
    }

    @Override
    public List<Pet> findAll() {

        List<Pet> foundItems = petRepo.findAll();

        if (foundItems.isEmpty()) throw new ResourceNotFoundException("Could not find any Pets.");

        return foundItems;

    }

    @Override
    public Pet findById(String id) {
        return petRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find Resource with provided ID: " + id));

    }

    @Override
    public Pet findByName(String name) {
        return petRepo.findByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException("Could not find Resource with provided Name: " + name));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Pet save(Pet pet) {

        if (pet.getPetId()!= "" && pet.getPetId() != null) throw new IllegalArgumentException("Pet ID Can't Be Present: "+ pet.getPetId());

        Owner owner = null;
        PetType petType =null;

        if (pet.getOwner() != null) owner = ownerRepo.findById(pet.getOwner().getOwnerId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Owner"));

        if (pet.getPetType().getTypeId() == null){
           petType = petTypeRepo.save(pet.getPetType());
        }else{
            petType = petTypeRepo.findById(pet.getPetType().getTypeId()).orElseThrow(() -> new ResourceNotFoundException("Invalid PetType"));
        }

        Pet toSave = new Pet(
                "",
                pet.getName(),
                pet.getBirthDate() == null ? LocalDate.now() : pet.getBirthDate(),
                petType, owner
        );

        toSave = petRepo.save(toSave);

        toSave.setOwner(owner);
        toSave.setPetType(petType);

        return toSave;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Pet update(Pet pet) {
        if (pet.getPetId() == null) throw new IllegalArgumentException("Invalid PetID");

        Pet persistedPet = petRepo.findById(pet.getPetId()).orElseThrow(() -> new ResourceNotFoundException("Could not find Resource with provided ID: " + pet.getPetId()));


        persistedPet.setName(pet.getName());
        persistedPet.setBirthDate(pet.getBirthDate());
        persistedPet.setPetType(pet.getPetType());
        persistedPet.setOwner(pet.getOwner());

        petRepo.save(persistedPet);

        return pet;
    }

    @Override
    public void delete(Pet pet) {
        petRepo.delete(pet);
    }
}
