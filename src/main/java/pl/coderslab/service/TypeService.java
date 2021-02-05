package pl.coderslab.service;

import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAllTypes();
}
