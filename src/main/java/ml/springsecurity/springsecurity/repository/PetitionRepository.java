package ml.springsecurity.springsecurity.repository;

import ml.springsecurity.springsecurity.entity.Petition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetitionRepository extends JpaRepository<Petition, Long> {
}
