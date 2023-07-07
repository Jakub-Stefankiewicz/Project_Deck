package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Offer;
import pl.coderslab.repository.EventRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void save(Event event) {
        eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isEmpty()) {
            throw new NotFoundException("Event not found");
        }
        return event.get();
    }

    public Event findFinal() {
        return eventRepository.findEventByFinalEventIsTrue();
    }

    public void delete(Event event) {
        eventRepository.delete(event);
    }

    public List<Event> findByEvents(Event event) {
        return eventRepository.findEventByEvents(event);
    }

    public List<Event> findByOffer(Offer offer) {
        return eventRepository.findAllByOffer(offer);
    }

    public Event findByTemplateIdAndOffer(Long templateId, Offer offer) {
        return eventRepository.findByTemplateIdAndOffer(templateId, offer);
    }

    public Event findFinalAndByOffer(Offer offer) {
        return eventRepository.findByOfferAndFinalEventIsTrue(offer);
    }

}
