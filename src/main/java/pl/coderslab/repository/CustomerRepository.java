package pl.coderslab.repository;

import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
