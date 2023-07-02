package pl.coderslab.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.EventRepository;

import java.util.List;

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
        return eventRepository.findById(id).get();
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
}
