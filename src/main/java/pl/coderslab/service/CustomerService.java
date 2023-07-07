package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Offer;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CustomerRepository;
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
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }
        return customer.get();
    }

    public Customer loadByUser(User user) {
        return customerRepository.findByUser(user);
    }

    public Customer findByOffer(Offer offer) {
        return customerRepository.findByOffer(offer);
    }

}
