package pl.coderslab.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    public Event findEventByFinalEventIsTrue();
    public List<Event> findEventByEvents(Event event);

}
