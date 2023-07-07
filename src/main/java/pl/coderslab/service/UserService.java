package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public void saveCustomer(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER");
        user.setRoles(new HashSet<>(Arrays.asList(customerRole)));
        userRepository.save(user);
    }

    public void saveDesigner(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role designerRole = roleRepository.findByName("ROLE_DESIGNER");
        user.setRoles(new HashSet<>(Arrays.asList(designerRole)));
        userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

}
