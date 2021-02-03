package pl.coderslab.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @CreatedBy
    private User user;

    @Column(unique = true)
    @Size(min=5, max = 127)
    private String title;

    @Size(min = 1,max = 4)
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Type> types;

    @Size(max = 255)
    private String destination;

    @Size(max = 10000)
    private String content;

    @CreatedDate
    private LocalDate created;

    @Past
    private LocalDate startDate;

    @Past
    private LocalDate endDate;
}
