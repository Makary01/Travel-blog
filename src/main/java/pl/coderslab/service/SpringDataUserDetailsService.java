package pl.coderslab.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.entity.User;
import pl.coderslab.repositories.UserRepository;
import pl.coderslab.utils.CurrentUser;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //FINDING USER IN DATABASE
        User user = null;
        try {
            user = userService.findByUserName(username);
        } catch (NotFoundException e) {
            e.printStackTrace();
            //no way to be thrown
        }

        //EXCEPTION IF USER NOT FOUND
        if (user == null) {throw new UsernameNotFoundException(username); }

        //ASSIGNING GRANTED AUTHORITIES BASED ON USER'S ROLES
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

        //RETURNING CURRENT USER
        return new CurrentUser(user.getUsername(),user.getPassword(),
                grantedAuthorities, user, userRepository);
    }
}
