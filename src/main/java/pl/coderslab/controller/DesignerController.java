package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Deal;
import pl.coderslab.entity.Designer;
import pl.coderslab.entity.Project;
import pl.coderslab.service.CustomCustomerDetailsService;
import pl.coderslab.service.CustomDesignerDetailsService;
import pl.coderslab.service.DealService;
import pl.coderslab.service.ProjectService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/designer")
public class DesignerController {

    private final CustomDesignerDetailsService customDesignerDetailsService;
    private final CustomCustomerDetailsService customCustomerDetailsService;
    private final DealService dealService;
    private final ProjectService projectService;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("designer")
    public Designer sessionDesigner(){
        //dodać pobieranego po ID
        return customDesignerDetailsService.loadDesignerById(1L);
    }

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

        model.addAttribute("designerCustomers", customCustomerDetailsService.loadAllCustomersByDesignerId(1L));
        return "designer/designer-home";
    }


    /**
     * After new customer is registered and assigned to designer, new deal is being created.
     * Deal is meant to be validated by designer and sent to customer for acceptation.
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(path = "/createdeal/{customerId}")
    String createDeal(@PathVariable("customerId") Long id, Model model) {
        Customer customer = customCustomerDetailsService.loadCustomerById(id);
        Deal deal = new Deal();
        deal.setCreated(LocalDate.now());
        deal.setValue(0.00);
        deal.setNotes("bez uwag");
        deal.setDesigner(sessionDesigner());
        deal.setCustomer(customer);
        model.addAttribute("deal", deal);
        return "designer/create-deal";
    }
    @PostMapping(path = "/createdeal/{customerId}")
    String saveDeal(Deal deal) {
        dealService.save(deal);
        return "/designer/designer-home";
    }

    @GetMapping(path = "/offer/add")
    String addOffer(Model model) {
        model.addAttribute("emptyOffer", new Project());
        model.addAttribute("offersList", projectService.findAllByDesignerId(sessionDesigner().getId()));
        return "designer/add-offer";
    }
    @PostMapping(path = "/offer/add")
    String saveOffer(Project project){
        project.setDesigner(sessionDesigner());
        projectService.save(project);
        return "redirect:/designer/offer/add";
    }
    @GetMapping(path = "/offer/delete/{id}")
    String deleteOffer(@PathVariable("id")Long id){
        projectService.delete(projectService.findById(id));
        return "redirect:/designer/offer/add";
    }
    @GetMapping(path = "/offer/edit/{id}")
    String editOffer(@PathVariable("id")Long id, Model model){
        model.addAttribute("offerToEdit", projectService.findById(id));
        return "designer/edit-offer";
    }
    @PostMapping(path = "/offer/edit/{id}")
    String saveEdited(Project project){
        projectService.save(project);
        return "redirect:/designer/offer/add";
    }

    @GetMapping(path = "/customers")
    String customers(Model model){
        model.addAttribute("designerCustomers", customCustomerDetailsService.loadAllCustomersByDesignerId(sessionDesigner().getId()));
        return "designer/customers";
    }

    @GetMapping(path = "/customer_details/{id}")
    String customerDetails(@PathVariable("id")Long id, Model model){
        model.addAttribute("customer", customCustomerDetailsService.loadCustomerById(id));
        return "designer/customer-details";
    }

    @GetMapping(path = "/customer/add_project/{id}")
    String addProjectToCustomer(@PathVariable Long id, Model model){
        model.addAttribute("customer", customCustomerDetailsService.loadCustomerById(id));
        model.addAttribute("projects", projectService.findAllByDesignerId(sessionDesigner().getId()));
        return "designer/add-project-to-customer";
    }
//    @PostMapping(path = "/customer/add_project/{id}")
//    String saveProjectToCustomer(Customer customer){
//        Customer customer=
//        customCustomerDetailsService.save();
//        customer.getProject();
//        return "designer/add-project-to-customer";
//    }



}
