package board.simpleboard.service;

import board.simpleboard.domain.Comment;
import board.simpleboard.domain.Member;
import board.simpleboard.domain.Post;
import board.simpleboard.domain.form.PostPageDto;
import board.simpleboard.domain.form.PostSearchCond;
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
    public void update(Long postId, Post post) {
        postRepository.findById(postId)
                .map(p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    return postRepository.save(p);
                });
    }

    //삭제
    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<PostPageDto>paging(PostSearchCond postSearchCond, Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 5; // 한 페이지에 5개씩

        Page<Post> postPages;
        Page<PostPageDto> postPagesDtos;
        // 검색조건이 없을 때
        if(postSearchCond.getTitle() == null && postSearchCond.getBoardName() == null) {
            postPages = postRepository.findAll(PageRequest.of(page, pageLimit, Sort.by("id").ascending()));
        }
        // 검색조건이 게시판이름일 때
        else if(postSearchCond.getTitle() == null) {
            postPages = postRepository.findAllByBoardName(postSearchCond.getBoardName(), PageRequest.of(page, pageLimit, Sort.by("id").ascending()));
        }
        // 검색조건이 제목일 때
        else if(postSearchCond.getBoardName() == null) {
            postPages = postRepository.findAllByTitleLike("%" + postSearchCond.getTitle() + "%", PageRequest.of(page, pageLimit, Sort.by("id").ascending()));
        }
        // 검색조건이 제목과 게시판이름일 때
        else {
            postPages = postRepository.findAllByTitleLikeAndBoardName("%" + postSearchCond.getTitle() + "%",  postSearchCond.getBoardName(), PageRequest.of(page, pageLimit, Sort.by("id").ascending()));
        }
        postPagesDtos = postPages.map(PostPageDto::new);

        return postPagesDtos;
    }


}
