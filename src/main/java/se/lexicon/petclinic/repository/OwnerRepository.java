package se.lexicon.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.petclinic.entity.Owner;

import java.util.Collection;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, String> {

    Optional<Owner> findByTelephone(String telephone);
    Collection<Owner> findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String firstName, String lastName);



}
