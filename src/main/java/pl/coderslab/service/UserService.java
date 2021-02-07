package pl.coderslab.service;

import javassist.NotFoundException;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;


public interface UserService {

    User findByUserName(String name) throws NotFoundException;

    User saveNewUser(User user) throws UniqueValuesException;

    User saveEditedUser(User user) throws UniqueValuesException;

    User findById(Long id) throws NotFoundException;

    Boolean checkPassword(String rawPass, String encodedPass);

    String encodePass(String passToEncode);

    void deleteUser(User user);

    void loadLast10Trips(User user);
}