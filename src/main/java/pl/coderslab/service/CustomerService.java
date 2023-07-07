package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Offer;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CustomerRepository;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> loadAllCustomersByDesignerId(Long id) {
        return customerRepository.findCustomersByDesignerId(id);
    }

    public Customer loadCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    public Customer loadByUser(User user){
        return customerRepository.findByUser(user);
    }

    public Customer findByOffer(Offer offer){
        return customerRepository.findByOffer(offer);
    }

}
