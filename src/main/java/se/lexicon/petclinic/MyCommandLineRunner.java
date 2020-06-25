package se.lexicon.petclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.petclinic.entity.Owner;
import se.lexicon.petclinic.entity.Pet;
import se.lexicon.petclinic.entity.PetType;
import se.lexicon.petclinic.repository.OwnerRepository;
import se.lexicon.petclinic.repository.PetRepository;
import se.lexicon.petclinic.repository.PetTypeRepository;


import java.time.LocalDate;

@Component
@Transactional(rollbackFor = Exception.class)
public class MyCommandLineRunner implements CommandLineRunner {

    PetTypeRepository petTypeRepository;
    OwnerRepository ownerRepository;
    PetRepository petRepository;

    @Autowired
    public MyCommandLineRunner(PetTypeRepository petTypeRepository, OwnerRepository ownerRepository, PetRepository petRepository) {
        this.petTypeRepository = petTypeRepository;
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        Owner owner = new Owner(null, "Simon", "Elbrink", "Nygatan 8", "0123456789");
        owner = ownerRepository.save(owner);

        PetType petType = new PetType(null, "Cat");
        petType = petTypeRepository.save(petType);

        Pet pet = new Pet("null", "Misse", LocalDate.parse("2010-01-01"), petType, owner);
        pet = petRepository.save(pet);



    }
}
