package ml.springsecurity.springsecurity.repository;

import ml.springsecurity.springsecurity.entity.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PetitionRepository extends PagingAndSortingRepository<Petition, Long> {
}
