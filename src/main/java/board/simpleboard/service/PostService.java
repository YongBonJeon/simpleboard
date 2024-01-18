package board.simpleboard.service;

import board.simpleboard.domain.Post;
import board.simpleboard.domain.User;
import board.simpleboard.repository.PostRepository;
import board.simpleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
