package board.simpleboard.service;

import board.simpleboard.domain.Member;
import board.simpleboard.domain.Post;
import board.simpleboard.domain.form.PostPageDto;
import board.simpleboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //조회
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
    public List<Post> findAllByMemberId(Member member) {
        log.info("member = {}", member);
        return postRepository.findAllByMemberId(member.getId());
    }

    //등록
    public Post save(Post post, Member loginMember) {
        Post savedPost = postRepository.save(post);
        savedPost.setMember(loginMember);
        return savedPost;
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

    public Page<PostPageDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5; // 한 페이지에 5개씩

        Page<Post> postPages = postRepository.findAll(PageRequest.of(page, pageLimit, Sort.by("id").ascending()));
        Page<PostPageDto> postPagesDtos = postPages.map(postPage -> new PostPageDto(postPage));

        return postPagesDtos;
    }
}
