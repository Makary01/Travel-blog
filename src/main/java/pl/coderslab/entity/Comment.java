package pl.coderslab.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Transactional
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @CreatedBy
    private User user;

    @ManyToOne
    private Trip trip;

    @NotNull
    @Size(max = 2055)
    private String content;

    @CreatedDate
    private LocalDate created;
}
