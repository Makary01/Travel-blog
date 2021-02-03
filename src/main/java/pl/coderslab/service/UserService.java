package pl.coderslab.service;

import javassist.NotFoundException;
import pl.coderslab.entity.User;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {

    User findByUserName(String name);

    User saveUser(User user) throws SQLIntegrityConstraintViolationException;

    User findById(Long id) throws NotFoundException;
}