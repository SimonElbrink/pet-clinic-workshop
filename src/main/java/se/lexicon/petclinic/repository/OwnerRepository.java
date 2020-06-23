package se.lexicon.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.petclinic.entity.Owner;

public interface OwnerRepository extends CrudRepository<Owner, String> {

    Owner findByTelephone(String telephone);


}
