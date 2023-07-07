package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Authorization;
import pl.coderslab.entity.Deal;
import pl.coderslab.entity.Offer;

import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    public Optional<Authorization> getAuthorizationsByCustomer_Id(Long id);
    public Optional<Authorization> findByOffer(Offer offer);
    public boolean existsByOffer(Offer offer);
}
