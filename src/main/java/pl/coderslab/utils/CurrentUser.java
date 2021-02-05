package pl.coderslab.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import pl.coderslab.repositories.UserRepository;

import java.util.Collection;
import java.util.Optional;

public class CurrentUser extends User {
    //USER TO RETURN
    private pl.coderslab.entity.User user;

    //REPOSITORY TO UPDATE USER
    private UserRepository userRepository;

    //CONSTRUCTOR
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.entity.User user, UserRepository userRepository) {
        super(username, password, authorities);
        this.user = user;
        this.userRepository = userRepository;
    }

    //GETTER FOR USER ENTITY
    public pl.coderslab.entity.User getUser() {
//        Optional<pl.coderslab.entity.User> entityUserOptional = userRepository.findById(user.getId());
//        if(entityUserOptional.isPresent()) {
//            user=entityUserOptional.get();
//        }
        return  this.user;
    }
}
