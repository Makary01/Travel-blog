package pl.coderslab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface TripRepository extends JpaRepository<Trip, Long>{



    List<Trip> findFirst10ByUserOrderByCreatedDesc(User user);

    boolean existsByTitle(String title);

    Optional<Trip> findByTitle(String title);

    Page<Trip> findDistinctByUserAndTypesIn(User user, Set<Type> types,Pageable pageable);

    Page<Trip> findDistinctByTypesIn(Set<Type> types, Pageable pageable);
}
