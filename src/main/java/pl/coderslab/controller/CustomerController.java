package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Designer;
import pl.coderslab.service.CustomCustomerDetailsService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomCustomerDetailsService customCustomerDetailsService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(path = "/add")
    String add(Model model) {
        model.addAttribute("emptyCustomer", new Customer());
        return "customer/register";
    }

    @PostMapping(path = "/add")
    String addNew(Customer customer, Model model) {
        customer.setAdded(LocalDate.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setActive(true);
        customCustomerDetailsService.save(customer);
        model.addAttribute("customer", customer);
        return "customer/success";
    }

    @GetMapping(path = "/home")
    String home() {
        return "customer/customerHome";
    }

}
