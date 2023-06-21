package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Designer;

import java.util.Optional;

@Repository
public interface DesignerRepository extends JpaRepository<Designer, Long> {
Optional<Designer> findByUsername(String username);
}
