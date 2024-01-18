package board.simpleboard.repository;

import board.simpleboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}
