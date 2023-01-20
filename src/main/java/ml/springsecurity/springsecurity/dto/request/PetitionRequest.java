package ml.springsecurity.springsecurity.dto.request;

import lombok.Getter;
import lombok.Setter;
import ml.springsecurity.springsecurity.entity.Category;

@Setter
@Getter
public class PetitionRequest {
    private Long id;
    private String title;
    private String description;
    private int gols;
    private Long categorie_id;

}
