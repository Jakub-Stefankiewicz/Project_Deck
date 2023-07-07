package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.security.CurrentUser;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {


        final User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("User " + login + " not found!"));

        final Collection<? extends GrantedAuthority> authorities =
                user
                        .getRoles()
                        .stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new CurrentUser(user.getLogin(), user.getPassword(), authorities, user);
    }
}
