package board.simpleboard.service;

import board.simpleboard.domain.User;
import board.simpleboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    //등록
    public User save(User user) {
        return userRepository.save(user);
    }

    //수정
    public void update(User user) {
        userRepository.save(user);
    }

    //삭제
    public void delete(User user) {
        userRepository.delete(user);
    }
}
