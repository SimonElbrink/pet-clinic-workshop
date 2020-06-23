package se.lexicon.petclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.petclinic.entity.Owner;
import se.lexicon.petclinic.service.OwnerService;

import java.util.List;

@RestController
@RequestMapping(path = "api/owner")
public class OwnerController {

    private OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Owner>> findAll(){
        return ResponseEntity.ok().body(ownerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable("id") String id){

        return ResponseEntity.ok().body(ownerService.findById(id));

    }
    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody Owner owner){

        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.create(owner));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable("id") String id){
        ownerService.delete(ownerService.findById(id));

        return ResponseEntity.noContent().build();
    }


}
