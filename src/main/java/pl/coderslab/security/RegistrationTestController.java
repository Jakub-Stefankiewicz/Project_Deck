package pl.coderslab.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class RegistrationTest {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @GetMapping
    @ResponseBody
    public boolean createTestUser(){
        User user=new User();
        user.setLogin("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(user);

        return true;
    }
}
