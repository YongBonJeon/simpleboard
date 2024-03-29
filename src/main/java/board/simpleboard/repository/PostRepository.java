package board.simpleboard.repository;

import board.simpleboard.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByTitleLike(String title, Pageable pageable);

    List<Post> findAllByMemberId(Long id);

    Page<Post> findAllByBoardName(String boardName, Pageable pageable);

    Page<Post> findAllByTitleLikeAndBoardName(String s, String s1, Pageable pageable);
}
