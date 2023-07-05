package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Designer;
import pl.coderslab.entity.User;
import pl.coderslab.service.CustomerService;
import pl.coderslab.service.DesignerService;
import pl.coderslab.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final CustomerService customerService;
    private final DesignerService designerService;


    @GetMapping(path = "/customer/{designer}")
    public String createCustomer(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "customer/register";
    }

    @PostMapping(path = "/customer/{designer}")
    public String saveCustomer(@PathVariable("designer")Long designer, User user){
        userService.saveCustomer(user);
        Long id=userService.findByLogin(user.getLogin()).getId();
        return "redirect:/register/add/customer/"+id+"/"+designer;
    }

    @GetMapping(path = "/add/customer/{id}/{designer}")
    public String addCustomer(@PathVariable("id") Long id,
                              @PathVariable("designer") Long designer, Model model){
        Customer customer=new Customer();
        customer.setUser(userService.findById(id));
        customer.setDesigner(designerService.loadDesignerById(designer));
        model.addAttribute("customer", customer);
        return "customer/add-customer";
    }

    @PostMapping(path = "/add/customer/{id}/{designer}")
    public String saveCustomerWithUser(Customer customer){
        customer.setActive(true);
        customer.setAdded(LocalDate.now());
        customerService.save(customer);
        return "home";
    }

    @GetMapping(path = "/designer")
    public String createDesigner(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "designer/register";
    }

    @PostMapping(path = "/designer")
    public String saveDesigner(User user){
        userService.saveDesigner(user);
        Long id=userService.findByLogin(user.getLogin()).getId();
        return "redirect:/register/add/designer/"+id;
    }

    @GetMapping(path = "/add/designer/{id}")
    public String addDesigner(@PathVariable("id") Long id, Model model){
        Designer designer=new Designer();
        designer.setUser(userService.findById(id));
        model.addAttribute("designer", designer);
        return "designer/add-designer";
    }

    @PostMapping(path = "/add/designer/{id}")
    public String saveDesignerWithUser(Designer designer){
        designer.setActive(true);
        designer.setAdded(LocalDate.now());
        designerService.save(designer);
        return "home";
    }

    }

