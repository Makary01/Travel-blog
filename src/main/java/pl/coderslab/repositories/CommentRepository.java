package pl.coderslab.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Trip;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findDistinctByTripOrderByCreatedDesc(Trip trip, Pageable pageable);


    void deleteByTrip(Trip trip);
}
