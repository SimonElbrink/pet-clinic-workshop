package se.lexicon.petclinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.petclinic.entity.Owner;
import se.lexicon.petclinic.repository.OwnerRepository;

import java.util.List;

@Service
public class OwnerServiceImpl implements  OwnerService{

    private OwnerRepository repo;

    @Autowired
    public OwnerServiceImpl(OwnerRepository repo) {
        this.repo = repo;
    }

    @Override
    public Owner findById(String ownerId) {
        return repo.findById(ownerId).get();
    }

    @Override
    public List<Owner> findAll() {
        return (List<Owner>) repo.findAll();
    }

    @Override
    public Owner findByTelephone(String telephone) {
        return repo.findByTelephone(telephone);
    }

    @Override
    public Owner create(Owner owner) {
        return repo.save(owner);
    }

    @Override
    public Owner update(Owner owner) {

        if(repo.findById(owner.getOwnerId()).isPresent()){
            return repo.save(owner);
        }

        return null;
    }

    @Override
    public void delete(Owner owner) {
        repo.delete(owner);
    }
}
