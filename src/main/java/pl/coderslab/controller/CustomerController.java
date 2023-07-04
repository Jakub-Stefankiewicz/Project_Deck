package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.collection.spi.PersistentBag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomCustomerDetailsService customCustomerDetailsService;
    private final CustomDesignerDetailsService customDesignerDetailsService;
    private final DealService dealService;
    private final AuthorizationService authorizationService;
    private final EventService eventService;
    private final OfferService offerService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(path = "/add/{designerId}")
    String add(Model model) {
        model.addAttribute("emptyCustomer", new Customer());
        return "customer/register";
    }

    @PostMapping(path = "/add/{designerId}")
    String addNew(@PathVariable("designerId") Long designerId, Customer customer, Model model) {
        Designer designer = customDesignerDetailsService.loadDesignerById(designerId);
        customer.setDesigner(designer);
        customer.setAdded(LocalDate.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setActive(true);
        customCustomerDetailsService.save(customer);
        model.addAttribute("customer", customer);
        return "customer/success";
    }

    @GetMapping(path = "/home")
    String home() {
        return "customer/customer-home";
    }

    @GetMapping(path = "/deal")
    String deal(Model model) {
        //dać ID zalogowanego customera
        model.addAttribute("deal", dealService.getByCustomerId(1L));
        return "customer/deal";
    }

    @GetMapping(path = "/deal/accepted")
    String dealAccepted(){
        Deal deal=dealService.getByCustomerId(1L);
        deal.setAccepted(true);
        dealService.save(deal);
        return "customer/customer-home";
    }

    @GetMapping(path = "/authorization")
    String authorization(Model model) {
        //dać ID zalogowanego customera
        model.addAttribute("authorization", authorizationService.findByCustomerId(1L));
        return "customer/authorization";
    }

    @GetMapping(path = "/tree")
    String showTree(Model model){
        model.addAttribute("event", eventService.findFinalAndByOffer(offerService.findByCustomer(customCustomerDetailsService.loadCustomerById(1L))));
        return "customer/show-tree";
    }

}
