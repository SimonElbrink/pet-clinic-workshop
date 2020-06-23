package se.lexicon.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.petclinic.entity.Pet;

public interface PetRepository extends CrudRepository<Pet, String> {
}
