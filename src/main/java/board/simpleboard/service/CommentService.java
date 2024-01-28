package board.simpleboard.service;

import board.simpleboard.domain.Comment;
import board.simpleboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //조회
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
    public List<Comment> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    //등록
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    //수정
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    public void update(Long commentId, String updateContent) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.setContent(updateContent);
    }

    //삭제
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
