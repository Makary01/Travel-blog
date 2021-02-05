package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;
import pl.coderslab.repositories.TripRepository;
import pl.coderslab.service.TripService;

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
    public List<Trip> findAllByUser(User user) {
        Optional<List<Trip>> optionalTripList = Optional.ofNullable(tripRepository.findAllByUser(user));
        return optionalTripList.isPresent() ? optionalTripList.get() : new ArrayList<Trip>();
    }

    @Override
    public List<Trip> findLatest5ByUser(User user) {
        Optional<List<Trip>> optionalTripList =
                Optional.ofNullable(tripRepository.findFirst5ByUserOrderByCreatedDesc(user));
        return optionalTripList.isPresent() ? optionalTripList.get() : new ArrayList<Trip>();
    }
}
