package ml.springsecurity.springsecurity.dto.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PetitionResponse {
    private Long id;
    private String title;
    private String description;
    private int gols;
    private String categorie;
}
