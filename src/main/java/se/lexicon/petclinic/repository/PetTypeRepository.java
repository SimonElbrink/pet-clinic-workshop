package se.lexicon.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.petclinic.entity.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Integer> {
}
