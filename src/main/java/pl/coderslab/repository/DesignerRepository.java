package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Designer;
import pl.coderslab.entity.User;


@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
    Designer findByUser(User user);
}
