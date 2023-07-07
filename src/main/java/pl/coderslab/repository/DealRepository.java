package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Deal;
import pl.coderslab.entity.Offer;
import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    Optional<Deal> getDealByCustomer_Id(Long id);

    Optional<Deal> findByOffer(Offer offer);

    boolean existsByOffer(Offer offer);

}
