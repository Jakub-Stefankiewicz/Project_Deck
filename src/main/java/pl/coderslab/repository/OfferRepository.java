package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Offer;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByDesignerId(Long id);

    List<Offer> findAllByDesignerIdAndAndTemplateIsTrue(Long id);

    List<Offer> findAllByEvents(Event event);

    Offer findByCustomer(Customer customer);
}
