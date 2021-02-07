package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Type;
import pl.coderslab.repositories.TypeRepository;
import pl.coderslab.service.TypeService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public List<Type> findAllList() {
        return typeRepository.findAll();
    }

    @Override
    public Set<Type> findByIdIn(Set<Long> ids) {
        return typeRepository.findDistinctByIdIn(ids);
    }

    @Override
    public Set<Type> findAllSet() {
        Set<Type> typesSet = new HashSet<>();
        for (Type type : typeRepository.findAll()) {
            typesSet.add(type);
        }
        return typesSet;
    }
}
