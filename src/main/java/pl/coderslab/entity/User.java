package pl.coderslab.entity;


import lombok.Data;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Transactional
@Entity
@Data
@Table(name = "users")

public class User {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5,max = 20,message = "Username must contain {min} to {max} charters")
    private String username;

    @NotNull(message = "Enter password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany
    private List<Trip> trips;

    @Email(message = "Enter valid email")
    @Size(min=5, max = 254, message = "Email must contain {min} to {max} charters")
    @NotNull(message = "Enter email")
    private String email;

    @Size(min = 2, max = 127,message = "Country must contain {min} to {max} charters")
    @NotNull(message = "Enter country name")
    private String country;

    @Size(min = 2, max = 127,message = "City must contain {min} to {max} charters")
    @NotNull(message = "Enter city name")
    private String city;

    @CreatedDate
    private LocalDate created;



    public Set<Role> getRoles() {
        return roles;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
