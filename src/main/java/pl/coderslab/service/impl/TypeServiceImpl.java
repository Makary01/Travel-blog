package pl.coderslab.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Type;
import pl.coderslab.repositories.TypeRepository;
import pl.coderslab.service.TypeService;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }
}
