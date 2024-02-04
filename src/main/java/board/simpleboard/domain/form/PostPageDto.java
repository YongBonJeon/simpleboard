package board.simpleboard.domain.form;

import board.simpleboard.domain.Post;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostPageDto {

    private Long id;
    private String title;
    private String content;
    private String name;
    private LocalDate createdDate;

    public PostPageDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.name = post.getMember().getName();
        this.createdDate = post.getCreatedDate();
    }
}
