package board.simpleboard.service;

import board.simpleboard.domain.Post;
import board.simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //조회
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
    //등록
    public Post save(Post post) {
        return postRepository.save(post);
    }

    //수정
    public void update(Post post) {
        postRepository.save(post);
    }

    //삭제
    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
