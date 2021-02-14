package pl.coderslab.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Trip;

import java.util.Optional;

public interface CommentService {

    void saveComment(Comment comment);

    Optional<Comment> findById(Long id);

    void deleteComment(Comment comment);

    Page<Comment> findTripsComments(Trip trip, Pageable pageable);
}
