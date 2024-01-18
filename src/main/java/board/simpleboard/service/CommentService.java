package board.simpleboard.service;

import board.simpleboard.domain.Comment;
import board.simpleboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //조회
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    //등록
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    //수정
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    //삭제
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
