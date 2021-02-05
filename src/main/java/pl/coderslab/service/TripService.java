package pl.coderslab.service;

import javassist.NotFoundException;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;

import java.util.List;
import java.util.Map;

public interface TripService {

    Map<Long, String> findAllByUserOnlyIdAndTitle(User user);

    List<Trip> findLatest10ByUser(User user);

    Trip findById(Long id) throws NotFoundException;

    Trip saveNewTrip(Trip trip) throws UniqueValuesException;

    Trip saveEditedTrip(Trip trip) throws UniqueValuesException;

    void delete(Trip trip);
}
