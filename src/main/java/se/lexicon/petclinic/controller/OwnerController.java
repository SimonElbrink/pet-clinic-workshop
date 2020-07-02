package se.lexicon.petclinic.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.petclinic.entity.Owner;
import se.lexicon.petclinic.exceptions.ResourceNotFoundException;
import se.lexicon.petclinic.repository.OwnerRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/owners")
public class OwnerController {

    public static final String ALL = "all";
    public static final String TELEPHONE = "telephone";
    public static final String OWNER_ID = "owner_id";
    public static final String NAME = "name";

    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerController(OwnerRepository ownerRepository) {

        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable("id") String id){
        Optional<Owner> optional = ownerRepository.findById(id);

        if (optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> find(
            @RequestParam(value = "type", defaultValue = ALL) String type,
            @RequestParam(value = "value", defaultValue = ALL) String value){

        switch (type.toLowerCase().trim()){

            case OWNER_ID: return ResponseEntity.ok().body(ownerRepository.findById(value).orElseThrow(() -> new ResourceNotFoundException("Could not find any Owner with ID: " + value)));

            case NAME:

                Collection<Owner> ownerList = ownerRepository.findByLastNameContainingIgnoreCaseOrFirstNameContainingIgnoreCase(value, value);

                if (ownerList.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }else {
                    return ResponseEntity.ok().body(ownerList);
                }

            case TELEPHONE:

                Optional<Owner> owner = ownerRepository.findByTelephone(value);

                if (owner.isPresent()){
                    return ResponseEntity.ok().body(owner);
                }else{
                    return ResponseEntity.noContent().build();
                }

            case ALL:
                return ResponseEntity.ok(ownerRepository.findAll());

            default: throw new IllegalArgumentException("Not a valid type: " + type);
        }

    }
    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody Owner owner){

        return ResponseEntity.status(HttpStatus.CREATED).body(ownerRepository.save(owner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@RequestBody Owner updated, @PathVariable("id") String id){

        if(!updated.getOwnerId().equals(id)) throw new IllegalArgumentException("ID does not match.");

        Owner original = helpMeFindById(id);
        original.setAddress(updated.getAddress());
        original.setFirstName(updated.getFirstName());
        original.setLastName(updated.getLastName());
        original.setTelephone(updated.getTelephone());

        original = ownerRepository.save(original);

        return ResponseEntity.ok(original);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable("id") String id){
        ownerRepository.delete(helpMeFindById(id));

        return ResponseEntity.noContent().build();
    }

    private Owner helpMeFindById(String id){
        return ownerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find any Owner with ID: "+ id));

    }


}
