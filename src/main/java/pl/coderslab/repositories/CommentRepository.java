package pl.coderslab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Trip;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {




}
