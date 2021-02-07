package pl.coderslab.service;

import pl.coderslab.entity.Type;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TypeService {
    List<Type> findAllList();

    Set<Type> findByIdIn(Set<Long> ids);

    Set<Type> findAllSet();
}
