package pl.coderslab.service;


import pl.coderslab.entity.Comment;

import java.util.Optional;

public interface CommentService {

    void saveComment(Comment comment);

    Optional<Comment> findById(Long id);

    void deleteComment(Comment comment);
}
