package pl.coderslab.entity;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 5,max = 20,message = "Username should contain 5 to 20 charters")
    private String username;

    @NotNull(message = "Password should contain 5 to 20 charters")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private Integer enabled;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Trip> trips;

    @Column(unique = true)
    @Email(message = "Enter valid email")
    @Size(min=5, max = 254, message = "Enter valid email")
    @NotNull(message = "Enter email")
    private String email;

    @Size(min = 2, max = 127,message = "Country name must contain at least 2 charters")
    @NotNull(message = "Enter country name")
    private String country;

    @Size(min = 2, max = 127,message = "City name must contain at least 2 charters")
    @NotNull(message = "Enter city name")
    private String city;

    @CreatedDate
    private LocalDate created;
}
