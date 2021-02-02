package pl.coderslab.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @NotNull
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails details;

    @NotNull
    @Size(min = 1,max = 1)
    private Integer isEnabled;

    @OneToMany
    private List<Comment> comments;

    @OneToMany
    private List<Trip> trips;
}
