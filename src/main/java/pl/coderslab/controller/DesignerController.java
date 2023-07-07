package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/designer")
public class DesignerController {

    private final DesignerService designerService;
    private final CustomerService customerService;
    private final DealService dealService;
    private final OfferService offerService;
    private final EventService eventService;
    private final AuthorizationService authorizationService;
    private final UserService userService;
    private final MailService mailService;


    @ModelAttribute("designer")
    public Designer sessionDesigner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());
        return designerService.findByUser(user);
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

        model.addAttribute("designerCustomers", customerService.loadAllCustomersByDesignerId(sessionDesigner().getId()));
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
        deal.setCustomer(customerService.loadCustomerById(id));
        if (customerService.loadCustomerById(id).getOffer() != null) {
            deal.setOffer(customerService.loadCustomerById(id).getOffer());
            deal.setValue(customerService.loadCustomerById(id).getOffer().getPrice());
        }
        model.addAttribute("deal", deal);
        return "designer/create-deal";
    }

    @PostMapping(path = "/createdeal/{customerId}")
    String saveDeal(Deal deal) {
        Customer customer=deal.getCustomer();
        customer.setDeal(deal);
        customerService.save(customer);
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
        Offer offerToClear = offerService.findById(id);
        //sprawdź czy jest coś przypisane do oferty, jak tak to usuń
        if(dealService.existsByOffer(offerToClear)) {
            Deal dealToClear = dealService.findByOffer(offerToClear);
            dealToClear.setOffer(null);
            dealService.save(dealToClear);
        }
        if(authorizationService.existsByOffer(offerToClear)) {
            Authorization authorizationToClear=authorizationService.findByOffer(offerToClear);
            authorizationToClear.setOffer(null);
            authorizationService.save(authorizationToClear);
        }
        Customer customerToClear=customerService.findByOffer(offerToClear);
        if(customerToClear!=null){
            customerToClear.setOffer(null);
            customerService.save(customerToClear);
        }
        List<Event> eventsToClear=eventService.findByOffer(offerToClear);
        for (Event event : eventsToClear) {
            event.setOffer(null);
            eventService.save(event);
        }
        offerService.delete(offerToClear);
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
        model.addAttribute("designerCustomers", customerService.loadAllCustomersByDesignerId(sessionDesigner().getId()));
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
        model.addAttribute("customer", customerService.loadCustomerById(id));
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
        model.addAttribute("customer", customerService.loadCustomerById(id));
        model.addAttribute("project", new Offer());
        model.addAttribute("projects", offerService.findByDesignerAndTemplate(sessionDesigner().getId()));
        return "designer/offer/add-offer-to-customer";
    }

    @PostMapping(path = "/customer/add_offer/{id}")
    String saveProjectToCustomer(Customer customer) {
        List<Event> eventsToCopy = eventService.findByOffer(customer.getOffer());
        Offer offer = new Offer();
        offer.setProjectType(customer.getOffer().getProjectType());
        offer.setCustomer(customer);
        offer.setDesigner(sessionDesigner());
        offer.setPrice(customer.getOffer().getPrice());
        offer.setTemplate(false);
        offerService.save(offer);
        //nadpisywanie offer id w customerze- dodajemy przed chwilą dodaną ofertę
        customer.setOffer(offerService.findByCustomer(customer));
        customerService.save(customer);


        for (Event event : eventsToCopy) {
            Event eventToAdd = new Event();
            eventToAdd.setCompleted(false);
            eventToAdd.setEndangered(false);
            eventToAdd.setEventName(event.getEventName());
            eventToAdd.setFinalEvent(event.isFinalEvent());
            eventToAdd.setTemplateId(event.getId());
            eventToAdd.setOffer(offerService.findByCustomer(customer));
            eventService.save(eventToAdd);
        }
        List<Event> eventsToAddDependencies = eventService.findByOffer(offerService.findByCustomer(customer));
        //wczytuje wszystkie nowe, skopiowane zależności
        for (Event event : eventsToAddDependencies) {
            //iteruje po skopiowanych zależnościach, bierze jedną, np. 35
            List<Event> eventList = new ArrayList<>();
            for (Event dependentEvent : eventService.findById(event.getTemplateId()).getEvents()) {
                //wrzucam w pętlę zależności z template eventu, który ma ID taki jak templateId skopiowanego eventu
                eventList.add(eventService.findByTemplateIdAndOffer(dependentEvent.getId(), event.getOffer()));
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
        if (customerService.loadCustomerById(id).getOffer() != null) {
            authorization.setOffer(customerService.loadCustomerById(id).getOffer());
        }
        authorization.setDesigner(sessionDesigner());
        authorization.setCustomer(customerService.loadCustomerById(id));
        model.addAttribute("authorization", authorization);
        return "designer/create-authorization";
    }

    @PostMapping(path = "/create_authorization/{id}")
    String saveAuthorization(Authorization authorization) {
        Customer customer=authorization.getCustomer();
        customer.setAuthorization(authorization);
        customerService.save(customer);
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
        model.addAttribute("designerCustomers", customerService.loadAllCustomersByDesignerId(sessionDesigner().getId()));
        return "designer/archives";
    }

    @PostMapping(path = "/customers")
    String sendEmail(@RequestParam("email") String email) {
        String subject="Witamy na platformie projektowej Project Deck!";
        String text="Witaj! \n"
                + "Zostałeś zaproszony do korzystania z platformy projektowej!\n"
                + "Utwórz swoje konto- klinkij w link : http://localhost:8080/register/customer/" + sessionDesigner().getId();
        mailService.sendMessage(email, subject, text);
        return "redirect:/designer/customers";
    }

    @GetMapping(path = "/archive/{id}")
    String archiveCustomer(@PathVariable("id") Long id){
        Customer customer=customerService.loadCustomerById(id);
        customer.setActive(false);
        customerService.save(customer);
        return "designer/designer-home";
    }

    @GetMapping(path = "/deal/show/{customerId}")
    String showDeal(@PathVariable Long customerId, Model model){
        model.addAttribute("deal", dealService.getByCustomerId(customerId));
        return "designer/show-deal";
    }

    @GetMapping(path = "/authorization/show/{customerId}")
    String showAuthorization(@PathVariable Long customerId, Model model){
        model.addAttribute("authorization", authorizationService.findByCustomerId(customerId));
        return "designer/show-authorization";
    }
}
