package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Offer;
import pl.coderslab.service.EventService;
import pl.coderslab.service.OfferService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/schema")
public class EventController {
    private final EventService eventService;
    private final OfferService offerService;

    /**
     * Show schemas: offer and events. Add, edit, delete event.
     *
     * @param model : model to create attribute.
     * @return show-schemas.jsp file with all list options.
     */
    @GetMapping(path = "/list")
    String showSchemas(Model model) {
        model.addAttribute("offers", offerService.findAll());
        model.addAttribute("event", new Event());
        model.addAttribute("events", eventService.findAll());
        return "designer/schemas/show-schemas";
    }

    @PostMapping(path = "/list")
    String saveTree(Event event) {
        if (event.getEventName() != null) {
            eventService.save(event);
        }
        return "redirect:/schema/list";
    }

    /**
     * Add selected events to offer.
     *
     * @param id  offer`s id.
     * @param model : model to create attribute.
     * @return offer-add-event.jsp file with events to select.
     */
    @GetMapping(path = "/offer/add_events/{id}")
    String addEventsToOffer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("offer", offerService.findById(id));
        model.addAttribute("events", eventService.findAll());
        return "designer/schemas/offer-add-event";
    }

    @PostMapping(path = "/offer/add_events/{id}")
    String saveEventsOnOffer(Offer offer) {
        offerService.save(offer);
        return "redirect:/schema/list";
    }

    /**
     * Edit selected event.
     *
     * @param id : event`s id.
     * @param model : model to create attribute.
     * @return : edit-event.jsp file with inputs to edit.
     */
    @GetMapping(path = "/event/edit/{id}")
    String editEvent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("eventToEdit", eventService.findById(id));
        return "designer/schemas/edit-event";
    }

    @PostMapping(path = "/event/edit/{id}")
    String saveEditedEvent(Event event) {
        eventService.save(event);
        return "redirect:/schema/list";
    }

    /**
     * Delete selected event.
     *
     * @param id : event`s id.
     * @return : redirect to schema list.
     */
    @GetMapping(path = "/event/delete/{id}")
    String deleteEvent(@PathVariable("id") Long id) {
        eventService.delete(eventService.findById(id));
        return "redirect:/schema/list";
    }

    /**
     * Adds dependencies to event- events that must be completed to complete selected event.
     *
     * @param id : event`s id to add dependencies.
     * @param model : model to create attribute.
     * @return add-dependencies.jsp file to select dependencies.
     */
    @GetMapping(path = "/dependencies/add/{id}")
    String addDependencies(@PathVariable("id") Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        List<Event> eventList=eventService.findAll();
        eventList.remove(eventService.findById(id));
        model.addAttribute("eventsList", eventList);
        return "designer/schemas/add-dependencies";
    }

    @PostMapping(path = "/dependencies/add/{id}")
    String saveDependencies(Event event) {
        eventService.save(event);
        return "redirect:/schema/list";
    }

}
