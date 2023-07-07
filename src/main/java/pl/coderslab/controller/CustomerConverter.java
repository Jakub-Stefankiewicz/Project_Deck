package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Event;
import pl.coderslab.repository.CustomerRepository;

public class CustomerConverter implements Converter<String, Customer> {
    @Autowired
    public CustomerRepository customerRepository;
    @Override
    public Customer convert(String source) {
        return customerRepository.findById(Long.parseLong(source)).get();
    }
}
