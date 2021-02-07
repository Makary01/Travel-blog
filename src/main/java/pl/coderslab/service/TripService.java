package pl.coderslab.service;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TripService {

    List<Trip> findLatest10ByUser(User user);

    Trip findById(Long id) throws NotFoundException;

    Trip saveNewTrip(Trip trip) throws UniqueValuesException;

    Trip saveEditedTrip(Trip trip) throws UniqueValuesException;

    void delete(Trip trip);

    Page<Trip> findPageByUserAndTypesOrderByX(User user, Set<Type> types, Pageable pageable);
}
