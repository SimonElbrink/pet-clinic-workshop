package se.lexicon.petclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.lexicon.petclinic.entity.Pet;

import se.lexicon.petclinic.service.PetService;

@RestController
@RequestMapping(path = "api/pets")
public class PetController {

    public static final String PET_NAME = "pet_name";
    public static final String ALL = "all";
    PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }


    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(name = "type", defaultValue = ALL) final String type,
            @RequestParam(name = "value", defaultValue = ALL) final String value
    ){
        switch (type.toLowerCase().trim()){
            case PET_NAME: return ResponseEntity.ok().body(petService.findByName(value.trim()));

            case ALL: return ResponseEntity.ok().body(petService.findAll());

            default: throw new IllegalArgumentException("Not a valid Type: " + type.trim());
        }
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet){

        Pet pet1 = petService.save(pet);

        return ResponseEntity.status(HttpStatus.CREATED).body(pet1);

    }


}
