package pet.project.javaworkforyou.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.project.javaworkforyou.comment.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
