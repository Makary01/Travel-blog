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
    @Size(min = 5,max = 20)
    @NotNull
    private String username;

    @Size(max = 255)
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    private Set<Role> roles;

    @NotNull
    private Integer enabled;

    @OneToMany
    private List<Comment> comments;

    @OneToMany
    private List<Trip> trips;

    @Email
    @Size(max = 127)
    private String email;

    @Size(max = 127)
    private String country;

    @Size(max = 127)
    private String city;

    @CreatedDate
    private LocalDate created;
}
