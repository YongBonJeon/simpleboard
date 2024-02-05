package board.simpleboard.repository;

import board.simpleboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    public List<Comment> findAllByPostId(Long postId);

    List<Comment> findAllByMemberId(Long id);
}
