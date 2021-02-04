package pl.coderslab.service;

import javassist.NotFoundException;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;


public interface UserService {

    User findByUserName(String name);

    User saveUser(User user) throws UniqueValuesException;

    User findById(Long id) throws NotFoundException;
}