package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/designer")
public class DesignerController {

    private final CustomDesignerDetailsService customDesignerDetailsService;
    private final CustomCustomerDetailsService customCustomerDetailsService;
    private final DealService dealService;
    private final OfferService offerService;
    private final EventService eventService;
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("designer")
    public Designer sessionDesigner() {
        //dodać pobieranego po ID
        return customDesignerDetailsService.loadDesignerById(1L);
    }

    /**
     * Adds new designer.
     *
     * @param model : model to create attribute.
     * @return : register.jsp file
     */
    @GetMapping(path = "/add")
    String add(Model model) {
        model.addAttribute("emptyDesigner", new Designer());
        return "designer/register";
    }

    @PostMapping(path = "/add")
    String addNew(Designer designer, Model model) {
        designer.setAdded(LocalDate.now());
        designer.setPassword(passwordEncoder.encode(designer.getPassword()));
        designer.setActive(true);
        customDesignerDetailsService.save(designer);
        model.addAttribute("designer", designer);
        return "designer/success";
    }

    /**
     * Designer homepage.
     *
     * @param model          : model to create attribute.
     * @param authentication : param to authenticate designer.
     * @return : designer-home.jsp file.
     */
    @GetMapping(path = "/home")
    String home(Model model, Authentication authentication) {
        //UWAGA: DOPISAĆ Auth POBIERAJĄCEGO ID Z AKTUALNIE ZALOGOWANEGO DESIGNERA I PRZEKAZUJĄCEGO DO PONIŻSZEJ METODY ew zmienić na load by designer username

        model.addAttribute("designerCustomers", customCustomerDetailsService.loadAllCustomersByDesignerId(1L));
        return "designer/designer-home";
    }


    /**
     * After new customer is registered and assigned to designer, new deal is being created.
     * Deal is meant to be validated by designer and sent to customer for acceptation.
     *
     * @param id    : customer id.
     * @param model : model to create attribute.
     * @return : create-deal.jsp file.
     */
    @GetMapping(path = "/createdeal/{id}")
    String createDeal(@PathVariable("id") Long id, Model model) {
        Deal deal = new Deal();
        deal.setCreated(LocalDate.now());
        deal.setNotes("bez uwag");
        deal.setDesigner(sessionDesigner());
        deal.setCustomer(customCustomerDetailsService.loadCustomerById(id));
        if (customCustomerDetailsService.loadCustomerById(id).getOffer() != null) {
            deal.setOffer(customCustomerDetailsService.loadCustomerById(id).getOffer());
            deal.setValue(customCustomerDetailsService.loadCustomerById(id).getOffer().getPrice());
        }
        model.addAttribute("deal", deal);
        return "designer/create-deal";
    }

    @PostMapping(path = "/createdeal/{customerId}")
    String saveDeal(Deal deal) {
        dealService.save(deal);
        return "redirect:/designer/customers";
    }

    /**
     * Show offers list.
     *
     * @param model : model to create attribute.
     * @return : show-offers.jsp file
     */
    @GetMapping(path = "/offers/list")
    String showOffers(Model model) {
        model.addAttribute("offers", offerService.findAllByDesignerId(sessionDesigner().getId()));
        return "designer/offer/show-offers";
    }


    /**
     * Create new template offer. Such offer is copied and added to customer, with
     * customer id and dates set.
     *
     * @param model : model to create attribute.
     * @return add-offer.jsp file.
     */
    @GetMapping(path = "/offer/add")
    String addOffer(Model model) {
        model.addAttribute("emptyOffer", new Offer());
        model.addAttribute("offersList", offerService.findAllByDesignerId(sessionDesigner().getId()));
        return "designer/offer/add-offer";
    }

    @PostMapping(path = "/offer/add")
    String saveOffer(Offer offer) {
        offer.setDesigner(sessionDesigner());
        offer.setTemplate(true);
        offerService.save(offer);
        return "redirect:/designer/offers/list";
    }

    /**
     * Delete selected offer.
     *
     * @param id : offer`s id to delete.
     * @return : redirect to offers list.
     */
    @GetMapping(path = "/offer/delete/{id}")
    String deleteOffer(@PathVariable("id") Long id) {
        offerService.delete(offerService.findById(id));
        return "redirect:/designer/offers/list";
    }

    /**
     * Edit selected offer.
     *
     * @param id    : offer`s id.
     * @param model : model to create attribute.
     * @return edit-offer.jsp file.
     */
    @GetMapping(path = "/offer/edit/{id}")
    String editOffer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("offerToEdit", offerService.findById(id));
        return "designer/offer/edit-offer";
    }

    @PostMapping(path = "/offer/edit/{id}")
    String saveEdited(Offer offer) {
        offerService.save(offer);
        return "redirect:/designer/offers/list";
    }

    /**
     * Show customers list
     *
     * @param model : model to create attribute.
     * @return customers.jsp fiel.
     */
    @GetMapping(path = "/customers")
    String customers(Model model) {
        model.addAttribute("designerCustomers", customCustomerDetailsService.loadAllCustomersByDesignerId(sessionDesigner().getId()));
        return "designer/customers";
    }

    /**
     * Show customer`s details.
     *
     * @param id    : customer`s id
     * @param model : model to create attribute.
     * @return : customer-details.jsp file.
     */
    @GetMapping(path = "/customer_details/{id}")
    String customerDetails(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customCustomerDetailsService.loadCustomerById(id));
        return "designer/customer-details";
    }

    /**
     * Add offer to customer.
     *
     * @param id    : customer`s id.
     * @param model : model to create attribute.
     * @return : add-offer-to-customer.jsp file.
     */
    @GetMapping(path = "/customer/add_offer/{id}")
    String addOfferToCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customCustomerDetailsService.loadCustomerById(id));
        model.addAttribute("project", new Offer());
        model.addAttribute("projects", offerService.findByDesignerAndTemplate(sessionDesigner().getId()));
        return "designer/offer/add-offer-to-customer";
    }

    @PostMapping(path = "/customer/add_offer/{id}")
    String saveProjectToCustomer(Customer customer) {
        List<Event> eventsToCopy=eventService.findByOffer(customer.getOffer());
        Offer offer=new Offer();
        offer.setProjectType(customer.getOffer().getProjectType());
        offer.setCustomer(customer);
        offer.setDesigner(sessionDesigner());
        offer.setPrice(customer.getOffer().getPrice());
        offer.setTemplate(false);
        customer.setOffer(null);
        customCustomerDetailsService.save(customer);
        offerService.save(offer);
        for (Event event : eventsToCopy){
            Event eventToAdd=new Event();
            eventToAdd.setCompleted(false);
            eventToAdd.setEndangered(false);
            eventToAdd.setEventName(event.getEventName());
            eventToAdd.setFinalEvent(event.isCompleted());
            eventToAdd.setTemplateId(event.getId());
            eventToAdd.setOffer(offerService.findByCustomer(customer));
            eventService.save(eventToAdd);
        }
        List<Event> eventsToAddDependencies=eventService.findByOffer(offerService.findByCustomer(customer));
        //wczytuje wszystkie nowe, skopiowane zależności
        for (Event event: eventsToAddDependencies) {
            //iteruje po skopiowanych zależnościach, bierze jedną, np. 35
            List<Event> eventList=new ArrayList<>();
            for (Event dependentEvent : eventService.findById(event.getTemplateId()).getEvents()) {
                //wrzucam w pętlę zależności z template eventu, który ma ID taki jak templateId skopiowanego eventu
                eventList.add(eventService.findByTemplateId(dependentEvent.getId()));
                //zapisuję do skopiowanego eventu inny skopiowany event o template id takim jak id templata z pętli
            }
            event.setEvents(eventList);
            eventService.save(event);
            //zapisuję
        }

        return "redirect:/designer/customers";
    }

    /**
     * @param id    : customer`s id.
     * @param model : model to create attribute.
     * @return : create-authorization.jsp file.
     */
    @GetMapping(path = "/create_authorization/{id}")
    String createAuthorization(@PathVariable Long id, Model model) {
        Authorization authorization = new Authorization();
        authorization.setCreated(LocalDate.now());
        if (customCustomerDetailsService.loadCustomerById(id).getOffer() != null) {
            authorization.setOffer(customCustomerDetailsService.loadCustomerById(id).getOffer());
        }
        authorization.setDesigner(sessionDesigner());
        authorization.setCustomer(customCustomerDetailsService.loadCustomerById(id));
        model.addAttribute("authorization", authorization);
        return "designer/create-authorization";
    }

    @PostMapping(path = "/create_authorization/{id}")
    String saveAuthorization(Authorization authorization) {
        authorizationService.save(authorization);
        return "redirect:/designer/customers";
    }

    /**
     * Show archived clients and projects
     *
     * @param model : model to add attribute
     * @return : archives.jsp file
     */
    @GetMapping(path = "/archives")
    String showArchives(Model model) {
        model.addAttribute("designerCustomers", customCustomerDetailsService.loadAllCustomersByDesignerId(sessionDesigner().getId()));
        return "designer/archives";
    }

    @PostMapping(path = "/customers")
    String sendEmail(@RequestParam("email") String email){
        System.out.println(email);
        return "redirect:/designer/customers";
    }
}
