package ml.springsecurity.springsecurity.service;

import ml.springsecurity.springsecurity.dto.request.PetitionRequest;
import ml.springsecurity.springsecurity.dto.response.PetitionResponse;
import ml.springsecurity.springsecurity.entity.Category;
import ml.springsecurity.springsecurity.entity.Petition;
import ml.springsecurity.springsecurity.repository.CategoryRepository;
import ml.springsecurity.springsecurity.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<PetitionResponse> getPetitions(int pageNumber, int pageSize){
        Pageable pages = PageRequest.of(pageNumber,pageSize, Sort.Direction.DESC, "id");
        List<Petition> petitions = petitionRepository.findAll(pages).getContent();
        List<PetitionResponse> petitionResponses = new ArrayList<>();
        for(Petition petition : petitions){
            PetitionResponse petitionResponse = new PetitionResponse();
            petitionResponse.setId(petition.getId());
            petitionResponse.setTitle(petition.getTitle());
            petitionResponse.setDescription(petition.getDescription());
            petitionResponse.setGols(petition.getGols());
            petitionResponse.setCategorie(petition.getCategory().getTitle());
            petitionResponses.add(petitionResponse);
        }
        return petitionResponses;
    }

    public PetitionResponse getSinglePetition(Long id){
        Optional<Petition> petition = petitionRepository.findById(id);
        if(petition.isEmpty()){
            return null;
        }
        PetitionResponse petitionResponse = new PetitionResponse();
        petitionResponse.setId(petition.get().getId());
        petitionResponse.setTitle(petition.get().getTitle());
        petitionResponse.setGols(petition.get().getGols());
        petitionResponse.setDescription(petition.get().getDescription());
        petitionResponse.setCategorie(petition.get().getCategory().getTitle());
        return petitionResponse;
    }

    public String deletePetition(Long id){
        Optional<Petition> petition = petitionRepository.findById(id);
        if(petition.isEmpty()){
            return "NOT FOUND";
        }
        petitionRepository.deleteById(id);
        return "DELETED";
    }


    public PetitionResponse savePetition(PetitionRequest petitionRequest){
        Category category = categoryRepository.findById(petitionRequest.getCategorie_id()).orElse(null);
        if(category == null){
            return null;
        }
        Petition petition = new Petition();
        if(petitionRequest.getId() != null){
            petition.setId(petitionRequest.getId());
        }
        petition.setCategory(category);
        petition.setGols(petitionRequest.getGols());
        petition.setDescription(petitionRequest.getDescription());
        petition.setTitle(petitionRequest.getTitle());
        petitionRepository.save(petition);

        PetitionResponse petitionResponse = new PetitionResponse();
        petitionResponse.setId(petition.getId());
        petitionResponse.setCategorie(petition.getCategory().getTitle());
        petitionResponse.setDescription(petition.getDescription());
        petitionResponse.setGols(petition.getGols());
        petitionResponse.setTitle(petition.getTitle());

        return petitionResponse;
    }

}
