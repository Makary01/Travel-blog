package pl.coderslab.service;

import pl.coderslab.entity.User;

public interface UserService {

    User findByUserName(String name);

    User saveUser(User user);

}