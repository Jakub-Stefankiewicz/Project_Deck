package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Customer;
import pl.coderslab.repository.CustomerRepository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomCustomerDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);

        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        Customer customer = optionalCustomer.get();


        return org.springframework.security.core.userdetails.User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_DESIGNER")))
                .build();

    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> loadAllCustomersByDesignerId(Long id) {
        return customerRepository.findCustomersByDesignerId(id);
    }

    public Optional<Customer> loadCustomerById(Long id) {
        return customerRepository.findById(id);
    }

}
