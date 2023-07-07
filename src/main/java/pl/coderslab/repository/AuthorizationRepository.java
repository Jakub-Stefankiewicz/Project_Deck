package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Authorization;
import pl.coderslab.entity.Offer;
import java.util.Optional;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    Optional<Authorization> getAuthorizationsByCustomer_Id(Long id);

    Optional<Authorization> findByOffer(Offer offer);

    boolean existsByOffer(Offer offer);
}
