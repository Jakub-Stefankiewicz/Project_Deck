package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Offer;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findEventByFinalEventIsTrue();

    List<Event> findEventByEvents(Event event);

    List<Event> findAllByOffer(Offer offer);

    Event findByTemplateIdAndOffer(Long templateId, Offer offer);

    Event findByOfferAndFinalEventIsTrue(Offer offer);

}
