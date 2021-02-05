package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {


    List<Trip> findFirst10ByUserOrderByCreatedDesc(User user);

    boolean existsByTitle(String title);

    Optional<Trip> findByTitle(String title);

    @Query("SELECT t.id FROM Trip t WHERE t.user = (:user)")
    List<Long> findAllByUserOrderByCreatedDescOnlyId(User user);

    @Query("SELECT t.title FROM Trip t WHERE t.user = (:user)")
    List<String> findAllByUserOrderByCreatedDescOnlyTitle(User user);

}
