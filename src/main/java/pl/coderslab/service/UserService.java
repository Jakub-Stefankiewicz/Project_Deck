package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

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
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return user.get();
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

}
