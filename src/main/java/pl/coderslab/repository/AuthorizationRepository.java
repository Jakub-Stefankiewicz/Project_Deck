package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Authorization;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    public Optional<Authorization> getAuthorizationsByCustomer_Id(Long id);
}
