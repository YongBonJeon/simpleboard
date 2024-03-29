package board.simpleboard.repository;

import board.simpleboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByEmail(String email);
}
