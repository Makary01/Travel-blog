package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}
