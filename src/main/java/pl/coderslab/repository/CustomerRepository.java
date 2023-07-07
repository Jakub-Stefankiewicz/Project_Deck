package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Customer;
import pl.coderslab.entity.Offer;
import pl.coderslab.entity.User;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomersByDesignerId(Long id);

    Customer findByUser(User user);

    Customer findByOffer(Offer offer);
}
