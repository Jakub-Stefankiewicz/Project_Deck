package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
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

    @GetMapping(path = "/tree/add")
    String addTree(Model model) {
        model.addAttribute("tree", new Tree());
        return "schemas/add-tree";
    }

    @PostMapping(path = "/tree/add")
    String saveTree(Tree tree) {
        treeService.save(tree);
        return "redirect:/schema/event/add";
    }

    @GetMapping(path = "/event/add")
    String add(Model model) {
        List<Tree> treeList = treeService.findAll();
        if (treeList.isEmpty()) {
            throw new NullPointerException("Tree list is empty");
        } else {
            model.addAttribute("treeList", treeList);
        }
        model.addAttribute("eventList", eventService.findAll());
        model.addAttribute("event", new Event());
        return "schemas/add-event";
    }

    @PostMapping(path = "/event/add")
    String save(Tree tree, Event event) {
        event.setTree((tree));
        eventService.save(event);
        return "redirect:/schema/event/add";
    }

    @GetMapping(path = "/dependencies")
    String dependencies(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "schemas/dependencies";
    }

    @GetMapping(path = "/dependencies/add/{id}")
    String addDependencies(@PathVariable("id") Long id, Model model) {
        model.addAttribute("event", eventService.findById(id));
        model.addAttribute("eventsList", eventService.findAll());
        return "schemas/add-dependencies";
    }

    @PostMapping(path = "/dependencies/add/{id}")
    String saveDependencies(Event event) {
        System.out.println(event);
        eventService.save(event);
        return "redirect:/schema/dependencies";
    }

    @GetMapping(path = "/test")
    @ResponseBody
    String test(){
        Event event=eventService.findFinal();
        System.out.println(event);



        System.out.println(event.getEvents());

        return "test";
    }


}
