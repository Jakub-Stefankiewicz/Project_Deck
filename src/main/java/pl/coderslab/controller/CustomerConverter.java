package pl.coderslab.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.acls.model.NotFoundException;
import pl.coderslab.entity.Customer;
import pl.coderslab.repository.CustomerRepository;
import java.util.Optional;

public class CustomerConverter implements Converter<String, Customer> {
    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public Customer convert(String source) {
        Optional<Customer> customer = customerRepository.findById(Long.parseLong(source));
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }
        return customer.get();
    }
}
