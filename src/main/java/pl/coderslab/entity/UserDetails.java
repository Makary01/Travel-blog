package pl.coderslab.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

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
