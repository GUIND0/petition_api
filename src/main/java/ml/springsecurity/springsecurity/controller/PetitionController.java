package ml.springsecurity.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetitionController {

    @GetMapping("/petitions")
    public ResponseEntity getPetitions(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
