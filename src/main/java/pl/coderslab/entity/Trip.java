package pl.coderslab.entity;

import lombok.Data;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;
@Transactional
@Entity
@Data
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @CreatedBy
    private User user;

    @Column(unique = true)
    @Size(min = 5, max = 127)
    @NotNull
    private String title;

    @Size(min = 1,max = 4)
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Type> types;

    @Size(min = 5,max = 255)
    @NotNull
    private String destination;

    @Size(min = 5,max = 10000)
    @NotNull
    private String content;

    @CreatedDate
    private LocalDate created;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "test startD")
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "test endD")
    private LocalDate endDate;
}
