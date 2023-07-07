package pl.coderslab.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.acls.model.NotFoundException;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.EventRepository;

import java.util.Optional;


public class EventConverter implements Converter<String, Event> {
    @Autowired
    public EventRepository eventRepository;

    @Override
    public Event convert(@NotNull String source) {
        Optional<Event> event=eventRepository.findById(Long.parseLong(source));
        if (event.isEmpty()){
            throw new NotFoundException("Event not found");
        }
        return event.get();
    }
}
