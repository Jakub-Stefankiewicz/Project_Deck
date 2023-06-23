package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Designer;
import pl.coderslab.repository.CustomerRepository;
import pl.coderslab.repository.DesignerRepository;

import java.util.Collections;
@Service
@RequiredArgsConstructor
public class CustomCustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer=customerRepository.findByUsername(username).get();

        return org.springframework.security.core.userdetails.User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_DESIGNER")))
                .build();

    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

}
