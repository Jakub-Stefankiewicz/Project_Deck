package pl.coderslab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Deal;
import java.util.Optional;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {

    public Optional<Deal> getDealByCustomer_Id(Long id);

}
