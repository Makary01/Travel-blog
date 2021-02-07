package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Type;

import java.util.List;
import java.util.Set;

public interface TypeRepository extends JpaRepository<Type, Long> {

    Set<Type> findDistinctByIdIn(Set<Long> ids);

    List<Type> findAll();
}
