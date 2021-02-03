package pl.coderslab.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    //CREATING USER TO RETURN
    private final pl.coderslab.entity.User user;

    //CONSTRUCTOR
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.entity.User user) {
        super(username, password, authorities);
        this.user = user;
    }

    //GETTER FOR USER ENTITY
    public pl.coderslab.entity.User getUser() {return user;}
}
