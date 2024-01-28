package board.simpleboard.domain.form;

import board.simpleboard.domain.Post;
import lombok.Data;

@Data
public class PostPageDto {

    private Long id;
    private String title;
    private String content;
    private String name;

    public PostPageDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.name = post.getMember().getName();
    }
}
