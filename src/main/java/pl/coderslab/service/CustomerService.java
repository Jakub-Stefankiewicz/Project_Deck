package pl.coderslab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Offer;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CustomerRepository;
import java.util.List;


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

    public Customer loadByUser(User user) {
        return customerRepository.findByUser(user);
    }

    public Customer findByOffer(Offer offer) {
        return customerRepository.findByOffer(offer);
    }

}
