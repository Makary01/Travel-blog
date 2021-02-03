package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.entity.User;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserRepository(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //FINDING USER IN DATABASE
        User user = userService.findByUserName(username);

        //EXCEPTION IF USER NOT FOUND
        if (user == null) {throw new UsernameNotFoundException(username); }

        //ASSIGNING GRANTED AUTHORITIES BASED ON USER'S ROLES
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

        //RETURNING CURRENT USER
        return new CurrentUser(user.getUsername(),user.getPassword(),
                grantedAuthorities, user);
    }
}
