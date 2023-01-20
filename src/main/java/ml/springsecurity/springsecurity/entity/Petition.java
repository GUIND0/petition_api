package ml.springsecurity.springsecurity.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "petitions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Petition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int gols;

    @ManyToOne
    private Category category;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedBy
    private Instant updatedAt;
}
