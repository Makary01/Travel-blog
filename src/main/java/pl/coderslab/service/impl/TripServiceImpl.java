package pl.coderslab.service.impl;


import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.repositories.TripRepository;
import pl.coderslab.service.TripService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }


    @Override
    public List<Trip> findLatest50ByUser(User user) {
        Optional<List<Trip>> optionalTripList = Optional.ofNullable(tripRepository.findFirst50ByUserOrderByCreatedDesc(user));
        return optionalTripList.isPresent() ? optionalTripList.get() : new ArrayList<Trip>();
    }

    @Override
    public List<Trip> findLatest5ByUser(User user) {
        Optional<List<Trip>> optionalTripList =
                Optional.ofNullable(tripRepository.findFirst5ByUserOrderByCreatedDesc(user));
        return optionalTripList.isPresent() ? optionalTripList.get() : new ArrayList<Trip>();
    }


    @Override
    public Trip findById(Long id) throws NotFoundException {
        Optional<Trip> tripOptional = tripRepository.findById(id);
        if (tripOptional.isPresent()) {
            return tripOptional.get();
        } else {
            throw new NotFoundException(id.toString());
        }
    }

    @Override
    public Trip saveNewTrip(Trip trip) throws UniqueValuesException {
        trip.setCreated(LocalDate.now());
        if (tripRepository.existsByTitle(trip.getTitle())) {
            throw new UniqueValuesException("title", "Title already taken");
        }
        return tripRepository.save(trip);
    }

    @Override
    public Trip saveEditedTrip(Trip trip) throws UniqueValuesException {
        Optional<Trip> tripByTitleOpt = tripRepository.findByTitle(trip.getTitle());
        if(tripByTitleOpt.isPresent()){
            if (tripByTitleOpt.get().getUser().getId() != trip.getId()) {
                throw new UniqueValuesException("title", "Title already taken");
            }
        }
        return tripRepository.save(trip);
    }

    @Override
    public void delete(Trip trip) {
        tripRepository.delete(trip);
    }

}
