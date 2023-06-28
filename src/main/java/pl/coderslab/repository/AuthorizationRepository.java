package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Authorization;
import pl.coderslab.entity.Deal;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    public Optional<Authorization> getAuthorizationsByCustomer_Id(Long id);
}
