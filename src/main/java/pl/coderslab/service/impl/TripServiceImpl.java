package pl.coderslab.service.impl;


import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.repositories.TripRepository;
import pl.coderslab.service.TripService;
import pl.coderslab.utils.SimpleTrip;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public Map<Long, String> findAllByUserOnlyIdAndTitle(User user) {
        List<Long> tripIdList;
        List<String> tripTitleList;
        Optional<List<Long>> optionalTripIdList = Optional.ofNullable(tripRepository.findAllByUserOrderByCreatedDescOnlyId(user));
        if(optionalTripIdList.isPresent()){
            tripIdList = optionalTripIdList.get();
            tripTitleList = tripRepository.findAllByUserOrderByCreatedDescOnlyTitle(user);
            return zipToMap(tripIdList,tripTitleList);
        }


        //        return optionalTripList.isPresent() ? optionalTripList.get() : new ArrayList<Trip>();
        return null;
    }

    @Override
    public List<Trip> findLatest10ByUser(User user) {
        Optional<List<Trip>> optionalTripList =
                Optional.ofNullable(tripRepository.findFirst10ByUserOrderByCreatedDesc(user));
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

    private <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }

}
