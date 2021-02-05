package pl.coderslab.service;

import javassist.NotFoundException;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;

import java.util.List;

public interface TripService {

    List<Trip> findAllByUser(User user);

    List<Trip> findLatest5ByUser(User user);

    Trip findById(Long id) throws NotFoundException;
}
