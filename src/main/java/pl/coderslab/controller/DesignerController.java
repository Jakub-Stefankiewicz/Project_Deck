package pl.coderslab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Designer;
import pl.coderslab.service.CustomDesignerDetailsService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/designer")
public class DesignerController {

    private final CustomDesignerDetailsService customDesignerDetailsService;

    private final PasswordEncoder passwordEncoder;

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

    @GetMapping(path = "/home")
    String home() {
        return "designer/designerHome";
    }

}
