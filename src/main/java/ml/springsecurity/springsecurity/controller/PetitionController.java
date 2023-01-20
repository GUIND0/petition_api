package ml.springsecurity.springsecurity.controller;

import ml.springsecurity.springsecurity.dto.request.PetitionRequest;
import ml.springsecurity.springsecurity.dto.response.PetitionResponse;
import ml.springsecurity.springsecurity.entity.Petition;
import ml.springsecurity.springsecurity.repository.PetitionRepository;
import ml.springsecurity.springsecurity.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PetitionController {

    @Autowired
    private PetitionRepository petitionRepository;

    @Autowired
    private PetitionService petitionService;

    @GetMapping("/petitions")
    public ResponseEntity<List<PetitionResponse>> getPetitions(@RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<List<PetitionResponse>>(petitionService.getPetitions(pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/petitions/{id}")
    public ResponseEntity<PetitionResponse> getSinglePetitionById(@PathVariable Long id){
        PetitionResponse petitionResponse = petitionService.getSinglePetition(id);
        if(petitionResponse == null){
            return new ResponseEntity<PetitionResponse>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PetitionResponse>(petitionResponse,HttpStatus.OK);
    }

    @PostMapping("/petitions")
    public ResponseEntity<PetitionResponse> savePetition(@Valid @RequestBody PetitionRequest petitionRequest){
        PetitionResponse petitionResponse = petitionService.savePetition(petitionRequest);
        if(petitionResponse == null){
            return new ResponseEntity<PetitionResponse>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PetitionResponse>(petitionResponse,HttpStatus.OK);
    }

    @DeleteMapping("/petitions/delete")
    public ResponseEntity<String> deletePetitionById(@RequestParam Long id){
        String response = petitionService.deletePetition(id);
        if(response.equalsIgnoreCase("NOT FOUND")){
            return new ResponseEntity<String>("NOT FOUND",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("DELETED",HttpStatus.OK);
    }

    @PutMapping("/petitions/{id}")
    public ResponseEntity<PetitionResponse> updatePetition(@PathVariable Long id,@Valid @RequestBody PetitionRequest petitionRequest){
        petitionRequest.setId(id);
        PetitionResponse petitionResponse = petitionService.savePetition(petitionRequest);
        if(petitionResponse == null){
            return new ResponseEntity<PetitionResponse>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<PetitionResponse>(petitionResponse,HttpStatus.OK);
    }


}
