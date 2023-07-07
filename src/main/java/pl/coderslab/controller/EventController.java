package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Designer;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Offer;
import pl.coderslab.entity.User;
import pl.coderslab.service.DesignerService;
import pl.coderslab.service.EventService;
import pl.coderslab.service.OfferService;
import pl.coderslab.service.UserService;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/schema")
public class EventController {
    private final EventService eventService;
    private final OfferService offerService;
    private final DesignerService designerService;
    private final UserService userService;

    /**
     * Get designer active in session.
     *
     * @return logged in designer.
     */
    @ModelAttribute("designer")
    public Designer sessionDesigner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());
        return designerService.findByUser(user);
    }

    /**
     * Show schemas. Also show events added to each schema.
     *
     * @param model : model to create attribute.
     * @return show-schemas.jsp file with all list options.
     */
    @GetMapping(path = "/list")
    String showSchemas(Model model) {
        model.addAttribute("offers", offerService
                .findByDesignerAndTemplate(sessionDesigner().getId()));
        model.addAttribute("events", eventService.findAll());
        return "designer/schemas/show-schemas";
    }

    /**
     * Add selected events to offer.
     *
     * @param id    offer`s id.
     * @param model : model to create attribute. Sends all events matched with offer.
     * @return offer-add-event.jsp file with events to set and select.
     */
    @GetMapping(path = "/offer/add_events/{id}")
    String addEventsToOffer(@PathVariable("id") Long id, Model model) {
        Offer offer = offerService.findById(id);
        Event event = new Event();
        event.setOffer(offer);
        model.addAttribute("offer", offer);
        model.addAttribute("event", event);
        model.addAttribute("events", eventService.findByOffer(offer));
        return "designer/schemas/offer-add-events";
    }

    @PostMapping(path = "/offer/add_events/{id}")
    String saveEventsOnOffer(Event event) {
        event.setId(null);
        if (event.isFinalEvent()) {
            for (Event eventToFinalRemove : eventService.findByOffer(event.getOffer())) {
                eventToFinalRemove.setFinalEvent(false);
            }
        }
        eventService.save(event);
        return "redirect:/schema/offer/add_events/" + event.getOffer().getId();
    }

    /**
     * Edit selected event.
     *
     * @param id    : event`s id.
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
        if (event.isFinalEvent()) {
            for (Event eventToFinalRemove : eventService.findByOffer(event.getOffer())) {
                eventToFinalRemove.setFinalEvent(false);
            }
        }
        eventService.save(event);
        return "redirect:/schema/offer/add_events/" + event.getOffer().getId();
    }

    /**
     * Delete selected event.
     * Get event name to be deleted. Than, clear event from event list matched
     * to offers and events. When event is removed from lists, remove it.
     *
     * @param id : event`s id.
     * @return : redirect to schema list.
     */
    @GetMapping(path = "/event/delete/{id}")
    String deleteEvent(@PathVariable("id") Long id) {
        Event eventToDelete = eventService.findById(id);
        List<Event> eventsToClear = eventService.findByEvents(eventToDelete);
        if (eventsToClear != null) {
            for (Event event : eventsToClear) {
                event.getEvents().remove(eventToDelete);
                eventService.save(event);
            }
        }
        List<Offer> offersToClear = offerService.findByEvent(eventToDelete);
        if (offersToClear != null) {
            for (Offer offer : offersToClear) {
                offer.getEvents().remove(eventToDelete);
                offerService.save(offer);
            }
        }
        eventService.delete(eventToDelete);
        return "redirect:/schema/offer/add_events/" + eventToDelete.getOffer().getId();
    }

    /**
     * Adds dependencies to event- events that must be completed to complete selected event.
     *
     * @param id    : event`s id to add dependencies.
     * @param model : model to create attribute.
     * @return add-dependencies.jsp file to select dependencies.
     */
    @GetMapping(path = "/dependencies/add/{id}")
    String addDependencies(@PathVariable("id") Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        Offer offer = eventService.findById(id).getOffer();
        List<Event> eventList = eventService.findByOffer(offer);
        eventList.remove(eventService.findById(id));
        model.addAttribute("eventsList", eventList);
        return "designer/schemas/add-dependencies";
    }

    @PostMapping(path = "/dependencies/add/{id}")
    String saveDependencies(Event event) {
        eventService.save(event);
        return "redirect:/schema/offer/add_events/" + event.getOffer().getId();
    }

    /**
     * Show project tree- offer and added events.
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(path = "/tree/{id}")
    String showTree(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventService.findFinal());
        return "designer/schemas/show-tree";
    }
}
