package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Tree;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
}
