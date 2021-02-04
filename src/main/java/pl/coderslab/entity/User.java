package pl.coderslab.entity;


import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 5,max = 20,message = "Username must contain {min} to {max} charters")
    private String username;

    @Size(min = 5, max = 60,message = "Password must contain {min} to {max} charters")
    @NotNull(message = "Enter password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Range(min = 0L, max = 1L)
    private Integer enabled;

    @Column(unique = true)
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
}
