package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Designer;
import pl.coderslab.service.DesignerService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class DesignerController {
    private final DesignerService designerService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping(path = "/add")
    String add(Model model){
        model.addAttribute("emptyDesigner", new Designer());
        return "designer/register";
    }

    @GetMapping(path = "proba")
    String homePage(){
        return "homepage";
    }

    @PostMapping(path = "/add")
    String addNew(Designer designer){
        designer.setAdded(LocalDate.now());
        designer.setPassword(passwordEncoder.encode(designer.getPassword()));
        designer.setActive(true);
        designerService.save(designer);
        return "designer/list";
    }

}
