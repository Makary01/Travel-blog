package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByUser(User user);

    List<Trip> findFirst5ByUserOrderByCreatedDesc(User user);

    boolean existsByTitle(String title);
}
