package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Event;
import pl.coderslab.entity.Tree;
import pl.coderslab.service.EventService;
import pl.coderslab.service.TreeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/schema")
public class EventController {
    private final EventService eventService;
    private final TreeService treeService;

    /**
     * Show schemas: trees and events. Add, edit, delete tree and event.
     *
     * @param model : model to create attribute.
     * @return show-schemas.jsp file with all list options.
     */
    @GetMapping(path = "/list")
    String showSchemas(Model model) {
        model.addAttribute("tree", new Tree());
        model.addAttribute("trees", treeService.findAll());
        model.addAttribute("event", new Event());
        model.addAttribute("events", eventService.findAll());
        return "designer/schemas/show-schemas";
    }

    @PostMapping(path = "/list")
    String saveTree(Tree tree, Event event) {
        if (tree.getTreeName() != null) {
            treeService.save(tree);
        }
        if (event.getEventName() != null) {
            eventService.save(event);
        }
        return "redirect:/schema/list";
    }

    /**
     * Add selected events to tree.
     *
     * @param id  tree`s id.
     * @param model : model to create attribute.
     * @return tree-add-event.jsp file with events to select.
     */
    @GetMapping(path = "/tree/add_events/{id}")
    String addEventsToTree(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tree", treeService.findById(id));
        model.addAttribute("events", eventService.findAll());
        return "designer/schemas/tree-add-event";
    }

    @PostMapping(path = "/tree/add_events/{id}")
    String saveEventsOnTree(Tree tree) {
        treeService.save(tree);
        return "redirect:/schema/list";
    }

    /**
     * Edit selected tree.
     *
     * @param id : tree`s id.
     * @param model : model to create attribute.
     * @return edit-tree.jsp file with inputs to edit.
     */
    @GetMapping(path = "/tree/edit/{id}")
    String editTree(@PathVariable("id") Long id, Model model) {
        model.addAttribute("treeToEdit", treeService.findById(id));
        return "designer/schemas/edit-tree";
    }

    @PostMapping(path = "/tree/edit/{id}")
    String saveEditedTree(Tree tree) {
        treeService.save(tree);
        return "redirect:/schema/list";
    }

    /**
     * Delete selected tree.
     *
     * @param id : tree`s id.
     * @return : redirect to schema list.
     */
    @GetMapping(path = "/tree/delete/{id}")
    String deleteTree(@PathVariable("id") Long id) {
        treeService.delete(treeService.findById(id));
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
        model.addAttribute("eventsList", eventService.findAll());
        return "designer/schemas/add-dependencies";
    }

    @PostMapping(path = "/dependencies/add/{id}")
    String saveDependencies(Event event) {
        eventService.save(event);
        return "redirect:/schema/list";
    }

}
