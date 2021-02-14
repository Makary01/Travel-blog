package pl.coderslab.service;

import javassist.NotFoundException;
import org.springframework.validation.ObjectError;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.utils.PasswordChanger;

import java.util.List;


public interface UserService {

    User findByUserName(String name) throws NotFoundException;

    User saveNewUser(User user) throws UniqueValuesException;

    User saveEditedUser(User user) throws UniqueValuesException;

    User findById(Long id) throws NotFoundException;

    Boolean checkPassword(String rawPass, String encodedPass);

    String encodePass(String passToEncode);

    void deleteUser(User user);

    void loadLast10Trips(User user);

    List<ObjectError> validateNewPassword(String username, PasswordChanger pc);

    void saveNewPassword(String username, String newPassword);
}