package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final DealService dealService;
    private final AuthorizationService authorizationService;
    private final EventService eventService;
    private final OfferService offerService;
    private final UserService userService;

    @ModelAttribute("customer")
    public Customer sessionCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());
        return customerService.loadByUser(user);
    }

    @GetMapping(path = "/home")
    String home() {
        return "customer/customer-home";
    }

    @GetMapping(path = "/deal")
    String deal(Model model) {
        model.addAttribute("deal", dealService.getByCustomerId(sessionCustomer().getId()));
        return "customer/deal";
    }

    @GetMapping(path = "/deal/accepted")
    String dealAccepted() {
        Deal deal = dealService.getByCustomerId(1L);
        deal.setAccepted(true);
        dealService.save(deal);
        return "customer/customer-home";
    }

    @GetMapping(path = "/authorization")
    String authorization(Model model) {
        //dać ID zalogowanego customera
        model.addAttribute("authorization", authorizationService.findByCustomerId(sessionCustomer().getId()));
        return "customer/authorization";
    }

    @GetMapping(path = "/tree")
    String showTree(Model model) {
        model.addAttribute("event", eventService.findFinalAndByOffer(offerService
                .findByCustomer(customerService.loadCustomerById(sessionCustomer().getId()))));
        return "customer/show-tree";
    }

}
