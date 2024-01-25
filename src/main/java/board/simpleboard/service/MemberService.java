package board.simpleboard.service;

import board.simpleboard.domain.Member;
import board.simpleboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;

    //조회
    public Optional<Member> findById(Long id) {
        return userRepository.findById(id);
    }
    //등록
    public Member save(Member member) {
        return userRepository.save(member);
    }

    //수정
    public void update(Member member) {
        userRepository.save(member);
    }

    //삭제
    public void delete(Member member) {
        userRepository.delete(member);
    }
}
