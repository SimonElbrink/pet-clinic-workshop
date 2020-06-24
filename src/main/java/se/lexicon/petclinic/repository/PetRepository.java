package se.lexicon.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.petclinic.entity.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, String> {

    Optional<Pet> findByNameIgnoreCase(String name);
    List<Pet> findAll();

}
