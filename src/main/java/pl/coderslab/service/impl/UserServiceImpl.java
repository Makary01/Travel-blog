package pl.coderslab.service.impl;

import javassist.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.repositories.RoleRepository;
import pl.coderslab.repositories.UserRepository;
import pl.coderslab.service.TripService;
import pl.coderslab.service.UserService;
import pl.coderslab.utils.PasswordChanger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TripService tripService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder, TripService tripService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tripService = tripService;
    }

    @Override
    public User findByUserName(String username) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NotFoundException(username);
        }
    }

    @Override
    public User saveNewUser(User user) throws UniqueValuesException {
        //ENCRYPTING PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //SETTING CREATED DATE
        LocalDate now = LocalDate.now();
        user.setCreated(now);

        //ASSIGNING DEFAULT ROLE
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        //CHECKING IF USERNAME IS AVAILABLE
        if (userRepository.existsByUsername(user.getUsername()))
            throw new UniqueValuesException("username", "Username already taken");

        //CHECKING IF EMAIL IS AVAILABLE
        if (userRepository.existsByEmail(user.getEmail()))
            throw new UniqueValuesException("email", "Email already taken");

        return userRepository.save(user);
    }

    @Override
    public User saveEditedUser(User user) throws UniqueValuesException {
        //CHECKING IF USERNAME IS AVAILABLE
        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
        if (userByUsername.isPresent()) {
            if (userByUsername.get().getId() != user.getId()) {
                throw new UniqueValuesException("username", "Username already taken");
            }
        }

        //CHECKING IF EMAIL IS AVAILABLE
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            if (userByEmail.get().getId() != user.getId()) {
                throw new UniqueValuesException("email", "Email already taken");
            }
        }

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new NotFoundException(id.toString());
        }
    }

    @Override
    public Boolean checkPassword(String rawPass, String encodedPass) {
        return passwordEncoder.matches(rawPass, encodedPass);
    }

    @Override
    public String encodePass(String passToEncode) {
        return passwordEncoder.encode(passToEncode);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void loadLast10Trips(User user) {
        user.setTrips(tripService.findLatest10ByUser(user));
    }

    @Override
    public List<ObjectError> validateNewPassword(String username, PasswordChanger pc) {
        List<ObjectError> errors = pc.validatePasswords();
        try {
            if (!checkPassword(pc.getCurrentPassword(), findByUserName(username).getPassword())) {
                errors.add(new ObjectError("currentPassword", "Current password is incorrect"));
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            //no way to get this thrown
        }
        return errors;
    }

    @Override
    public void saveNewPassword(String username, String newPassword) {
        User user;
        try {
            user = findByUserName(username);
            user.setPassword(encodePass(newPassword));
            saveEditedUser(user);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (UniqueValuesException e) {
            e.printStackTrace();
            //no unique values edited
        }

    }

}
