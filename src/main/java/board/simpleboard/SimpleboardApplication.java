package board.simpleboard;

import board.simpleboard.domain.Member;
import board.simpleboard.domain.Post;
import board.simpleboard.service.CommentService;
import board.simpleboard.service.MemberService;
import board.simpleboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
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
		Member memberA = new Member("a", "a", "a");
		Member memberB = new Member("b", "b", "b");
		Member memberC = new Member("c", "c", "c");
		Member memberD = new Member("d", "d", "d");
		Member memberE = new Member("e", "e", "e");
		memberService.save(memberA);
		memberService.save(memberB);
		memberService.save(memberC);
		memberService.save(memberD);
		memberService.save(memberE);

		for(int i = 1 ; i <= 30 ; i++) {
			postService.save(new Post("title" + i, "content" + i), memberA);
		}


	}

}
