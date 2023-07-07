package pl.coderslab.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Designer;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserToRegister;
import pl.coderslab.service.CustomerService;
import pl.coderslab.service.DesignerService;
import pl.coderslab.service.MailService;
import pl.coderslab.service.UserService;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final CustomerService customerService;
    private final DesignerService designerService;



    /**
     * Create and save new user- customer. Path must be created with designer id.
     * In Post method, because of password encrypting, validation goes through additional class- UserToRegister.
     *
     * @param model : model to create attribute
     * @return customer/register.jsp form
     */
    @GetMapping(path = "/customer/{designer}")
    public String createCustomer(Model model){
        model.addAttribute("userToRegister", new UserToRegister());
        return "customer/register";
    }

    @PostMapping(path = "/customer/{designer}")
    public String saveCustomer(@PathVariable Long designer, @Valid UserToRegister userToRegister, BindingResult result, Model model){
        if(result.hasErrors()) {
            return "customer/register";
        }
        if(userService.existsByLogin(userToRegister.getUsername())){
            String exists="Nazwa użytkownika zajęta: podaj inną nazwę";
            model.addAttribute("exists", exists);
            return "customer/register";
        }
        User user=new User();
        user.setLogin(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        userService.saveCustomer(user);
        Long id=userService.findByLogin(user.getLogin()).getId();
        return "redirect:/register/add/customer/"+id+"/"+designer;
    }

    /**
     * Create and save customer details.
     *
     * @param id : new user to be matched with customer id
     * @param designer : designer`s id
     * @param model :  model to create attribute
     * @return customer/add-customer.jsp view
     */
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
    public String saveCustomerWithUser(@Valid Customer customer, BindingResult result){
        if(result.hasErrors()){
            return "customer/add-customer";
        }
        customer.setActive(true);
        customer.setAdded(LocalDate.now());
        customerService.save(customer);
        return "home";
    }

    /**
     * Create and save user- designer.
     * In Post method, because of password encrypting, validation goes through additional class- UserToRegister.
     *
     * @param model : model to add attribute.
     * @return designer/register.jsp form
     */
    @GetMapping(path = "/designer")
    public String createDesigner(Model model){
        model.addAttribute("userToRegister", new UserToRegister());
        return "designer/register";
    }

    @PostMapping(path = "/designer")
    public String saveDesigner(@Valid UserToRegister userToRegister, BindingResult result, Model model){
        if(result.hasErrors()) {
            return "designer/register";
        }
        if(userService.existsByLogin(userToRegister.getUsername())){
            String exists="Nazwa użytkownika zajęta: podaj inną nazwę";
            model.addAttribute("exists", exists);
            return "designer/register";
        }
        User user=new User();
        user.setLogin(userToRegister.getUsername());
        user.setPassword(userToRegister.getPassword());
        userService.saveDesigner(user);
        Long id=userService.findByLogin(user.getLogin()).getId();
        return "redirect:/register/add/designer/"+id;
    }

    /**
     * Add designer details to created user.
     *
     * @param id : designer id
     * @param model : model to create attribute
     * @return : designer/add-designer.jsp view
     */
    @GetMapping(path = "/add/designer/{id}")
    public String addDesigner(@PathVariable("id") Long id, Model model){
        Designer designer=new Designer();
        designer.setUser(userService.findById(id));
        model.addAttribute("designer", designer);
        return "designer/add-designer";
    }

    @PostMapping(path = "/add/designer/{id}")
    public String saveDesignerWithUser(@Valid Designer designer, BindingResult result){
        if(result.hasErrors()){
            return "designer/add-designer";
        }
        designer.setActive(true);
        designer.setAdded(LocalDate.now());
        designerService.save(designer);
        return "home";
    }

    }

