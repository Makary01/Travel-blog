package pl.coderslab.service;

import javassist.NotFoundException;
import pl.coderslab.entity.User;

public interface UserService {

    User findByUserName(String name);

    User saveUser(User user);

    User findById(Long id) throws NotFoundException;
}