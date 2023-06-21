package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Designer;
import pl.coderslab.repository.DesignerRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomDesignerDetailsService implements UserDetailsService {
    private final DesignerRepository designerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Designer designer=designerRepository.findByUsername(username).get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(designer.getUsername())
                .password(designer.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_DESIGNER")))
                .build();

    }
}
