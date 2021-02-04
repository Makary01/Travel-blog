package pl.coderslab.service.impl;

import javassist.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.repositories.RoleRepository;
import pl.coderslab.repositories.UserRepository;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.UserService;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveNewUser(User user) throws UniqueValuesException {
        //ENCRYPTING PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //ACTIVATING USER
        user.setEnabled(1);

        //SETTING CREATED DATE
        LocalDate now = LocalDate.now();
        user.setCreated(now);

        //ASSIGNING DEFAULT ROLE
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        //CHECKING IF USERNAME IS AVAILABLE
        if(userRepository.existsByUsername(user.getUsername()))
            throw new UniqueValuesException("username","Username already taken");

        //CHECKING IF EMAIL IS AVAILABLE
        if(userRepository.existsByEmail(user.getEmail()))
            throw new UniqueValuesException("email","Email already taken");

        return userRepository.save(user);
    }

    @Override
    public User saveEditedUser(User user) throws UniqueValuesException {
        //CHECKING IF USERNAME IS AVAILABLE
        Optional<User> userByUsername = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        if(userByUsername.isPresent()){
            if(userByUsername.get().getId() != user.getId()){
                throw new UniqueValuesException("username","Username already taken");
            }
        }

        //CHECKING IF EMAIL IS AVAILABLE
        Optional<User> userByEmail = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if(userByEmail.isPresent()){
            if(userByEmail.get().getId() != user.getId()){
                throw new UniqueValuesException("email","Email already taken");
            }
        }

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return userOptional.get();
        }else {
            throw new NotFoundException(id.toString());
        }
    }

    @Override
    public Boolean checkPassword(String rawPass, String encodedPass){
        return passwordEncoder.matches(rawPass,encodedPass);
    }

    @Override
    public String encodePass(String passToEncode) {
        return passwordEncoder.encode(passToEncode);
    }

}
