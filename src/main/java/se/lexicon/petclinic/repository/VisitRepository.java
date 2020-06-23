package se.lexicon.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.petclinic.entity.Pet;
import se.lexicon.petclinic.entity.Visit;

import java.time.LocalDate;

public interface VisitRepository extends CrudRepository<Visit, String> {

    Visit findByVisitDateBefore(LocalDate end);
    Visit findByVisitDateAfter(LocalDate start);

    Visit findByPet(Pet pet);
}
