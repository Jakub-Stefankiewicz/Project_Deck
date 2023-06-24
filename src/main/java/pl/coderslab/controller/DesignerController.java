package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Authorization;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Deal;
import pl.coderslab.entity.Designer;
import pl.coderslab.service.AuthorizationService;
import pl.coderslab.service.CustomCustomerDetailsService;
import pl.coderslab.service.CustomDesignerDetailsService;
import pl.coderslab.service.DealService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/designer")
public class DesignerController {

    private final CustomDesignerDetailsService customDesignerDetailsService;
    private final CustomCustomerDetailsService customCustomerDetailsService;
    private final DealService dealService;
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Adds new designer.
     *
     * @param model
     * @return
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
     * @param model
     * @param authentication
     * @return
     */
    @GetMapping(path = "/home")
    String home(Model model, Authentication authentication) {
        //UWAGA: DOPISAĆ Auth POBIERAJĄCEGO ID Z AKTUALNIE ZALOGOWANEGO DESIGNERA I PRZEKAZUJĄCEGO DO PONIŻSZEJ METODY ew zmienić na load by designer username

        model.addAttribute("designerCustomers", customCustomerDetailsService.loadAllCustomersByDesignerId(2L));
        return "designer/designer-home";
    }


    /**
     * After new customer is registered and assigned to designer, new deal is being created.
     * Deal is meant to be validated by designer and sent to customer for acceptation.
     *
     * @param id : customer ID from url
     * @param model
     * @return
     */
    @GetMapping(path = "/createdeal/{customerId}")
    String createDeal(@PathVariable("customerId") Long id, Model model) {
        Customer customer = customCustomerDetailsService.loadCustomerById(id).get();
        //Tu też pobrać zalogowanego designera
        Designer designer = customDesignerDetailsService.loadDesignerById(2L);
        Deal deal = new Deal();
        deal.setCreated(LocalDate.now());
        deal.setValue(0.00);
        deal.setNotes("bez uwag");
        deal.setDesigner(designer);
        deal.setCustomer(customer);
        model.addAttribute("deal", deal);
        return "designer/create-deal";
    }
    @PostMapping(path = "/createdeal/{customerId}")
    String saveDeal(Deal deal) {
        dealService.save(deal);
        return "/designer/designer-home";
    }

    /**
     * After new customer is registered and assigned to designer, new authorization is being created.
     * Authorization is meant to be validated by designer and sent to customer for acceptation.
     *
     * @param id : customer Id from url
     * @param model
     * @return
     */
    @GetMapping(path = "/createauthorization/{customerId}")
    String createAuthorization(@PathVariable("customerId") Long id, Model model){
        Customer customer = customCustomerDetailsService.loadCustomerById(id).get();
        //Tu też pobrać zalogowanego designera
        Designer designer = customDesignerDetailsService.loadDesignerById(2L);
        Authorization authorization=new Authorization();
        authorization.setCreated(LocalDate.now());
        authorization.setDesigner(designer);
        authorization.setCustomer(customer);
        model.addAttribute("authorization", authorization);
        return "designer/create-authorization";
    }
    @PostMapping(path = "/createauthorization/{customerId}")
    String saveAuthorization(Authorization authorization){
        authorizationService.save(authorization);
        return "designer/designer-home";
    }

}
