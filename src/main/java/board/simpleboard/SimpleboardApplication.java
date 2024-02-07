package board.simpleboard;

import board.simpleboard.domain.Address;
import board.simpleboard.domain.Member;
import board.simpleboard.domain.Post;
import board.simpleboard.domain.Role;
import board.simpleboard.service.CommentService;
import board.simpleboard.service.MemberService;
import board.simpleboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SimpleboardApplication {

	@Autowired private MemberService memberService;
	@Autowired
	private PostService postService;
	@Autowired private CommentService commentService;


	public static void main(String[] args) {
		SpringApplication.run(SimpleboardApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		Member memberAdmin = new Member("관리자admin", "admin", "admin", "bon6143@gmail.com", "010-9093-6143", new Address("서울", "송파", "05652"), Role.ADMIN);
		Member memberA = new Member("사용자a", "a", "a", "a@naver.com", "010-1111-1111", new Address("서울", "송파", "05652"), Role.USER);
		Member memberB = new Member("사용자b", "b", "b", "b@naver.com", "010-2222-2222", new Address("서울", "송파", "05652"), Role.USER);
		Member memberC = new Member("사용자c", "c", "c", "c@naver.com", "010-3333-3333", new Address("서울", "송파", "05652"), Role.USER);
		Member memberD = new Member("사용자d", "d", "d", "d@naver.com", "010-4444-4444", new Address("서울", "송파", "05652"), Role.USER);
		Member memberE = new Member("사용자e", "e", "e", "e@naver.com", "010-5555-5555", new Address("서울", "송파", "05652"), Role.USER);
		if(memberAdmin.getRole().getKey() == "ADMIN") {
			memberAdmin.setRole(Role.ADMIN);
		}
		memberService.save(memberAdmin);
		memberService.save(memberA);
		memberService.save(memberB);
		memberService.save(memberC);
		memberService.save(memberD);
		memberService.save(memberE);

		for(int i = 1 ; i <= 14 ; i++) {
			postService.save(new Post("title" + i, "content" + i, "자유게시판"), memberA);
		}
		for(int i = 15 ; i <= 28 ; i++) {
			postService.save(new Post("title" + i, "content" + i, "질문게시판"), memberB);
		}
		for(int i = 29 ; i <= 30 ; i++) {
			postService.save(new Post("title" + i, "content" + i, "공지사항"), memberAdmin);
		}


	}

}
