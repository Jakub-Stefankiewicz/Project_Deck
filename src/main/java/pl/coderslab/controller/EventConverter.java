package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.EventRepository;


public class EventConverter implements Converter<String, Event> {
    @Autowired
    public EventRepository eventRepository;

    @Override
    public Event convert(String source) {
        return eventRepository.findById(Long.parseLong(source)).get();
    }
}
