package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.*;
import pl.coderslab.service.*;


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


    /**
     * Get customer active in session.
     *
     * @return logged in customer.
     */
    @ModelAttribute("customer")
    public Customer sessionCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(authentication.getName());
        return customerService.loadByUser(user);
    }

    /**
     * Show custoer`s homepage.
     *
     * @return customer/customer-home.jsp view.
     */
    @GetMapping(path = "/home")
    String home() {
        return "customer/customer-home";
    }

    /**
     * Show deal.
     *
     * @param model : model to set attribute.
     * @return customer/deal.jsp view.
     */
    @GetMapping(path = "/deal")
    String deal(Model model) {
        model.addAttribute("deal", dealService.getByCustomerId(sessionCustomer().getId()));
        return "customer/deal";
    }

    /**
     * Accept deal.
     *
     * @return customer/customer-home.jsp view.
     */
    @GetMapping(path = "/deal/accepted")
    String dealAccepted() {
        Deal deal = dealService.getByCustomerId(sessionCustomer().getId());
        deal.setAccepted(true);
        dealService.save(deal);
        return "customer/customer-home";
    }

    /**
     * Show authorization.
     *
     * @param model : model to set attribute.
     * @return customer/authorization.jsp view.
     */
    @GetMapping(path = "/authorization")
    String authorization(Model model) {
        model.addAttribute("authorization", authorizationService.findByCustomerId(sessionCustomer().getId()));
        return "customer/authorization";
    }

    /**
     * Accept authorization.
     *
     * @return : customer/customer-home.jsp view
     */
    @GetMapping(path = "/authorization/accepted")
    String authorizationAccepted() {
        Authorization authorization = authorizationService.findByCustomerId(sessionCustomer().getId());
        authorization.setAccepted(true);
        authorizationService.save(authorization);
        return "customer/customer-home";
    }

    /**
     * Show project tree.
     *
     * @param model : model to set attribute.
     * @return customer/show-tree.jsp view.
     */
    @GetMapping(path = "/tree")
    String showTree(Model model) {
        model.addAttribute("event", eventService.findFinalAndByOffer(offerService
                .findByCustomer(customerService.loadCustomerById(sessionCustomer().getId()))));
        return "customer/show-tree";
    }

}
