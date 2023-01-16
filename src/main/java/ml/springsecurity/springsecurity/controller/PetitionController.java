package ml.springsecurity.springsecurity.controller;

import ml.springsecurity.springsecurity.entity.Petition;
import ml.springsecurity.springsecurity.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PetitionController {

    @Autowired
    private PetitionRepository petitionRepository;

    @GetMapping("/petitions")
    public ResponseEntity<List<Petition>> getPetitions(@RequestParam int pageNumber, @RequestParam int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return new ResponseEntity<List<Petition>>(petitionRepository.findAll(pageable).getContent(),HttpStatus.OK);
    }

    @GetMapping("/petitions/{id}")
    public ResponseEntity<Petition> getSinglePetitionById(@PathVariable Long id){
        Optional<Petition> petition = petitionRepository.findById(id);
        if(petition.isEmpty()){
            return new ResponseEntity<Petition>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Petition>(petition.get(),HttpStatus.OK);
    }

    @PostMapping("/petitions")
    public ResponseEntity<Petition> savePetition(@RequestBody Petition petition){
        return new ResponseEntity<Petition>(petitionRepository.save(petition),HttpStatus.OK);
    }

    @DeleteMapping("/petitions/delete")
    public ResponseEntity<String> deletePetitionById(@RequestParam Long id){
        Optional<Petition> petition = petitionRepository.findById(id);
        if(petition.isEmpty()){
            return new ResponseEntity<String>("NOT FOUND",HttpStatus.NOT_FOUND);
        }
        petitionRepository.deleteById(id);
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }

    @PutMapping("/petitions/{id}")
    public ResponseEntity<Petition> updatePetition(@PathVariable Long id,@RequestBody Petition petition){
        petition.setId(id);
        return new ResponseEntity<Petition>(petitionRepository.save(petition),HttpStatus.OK);
    }


}
