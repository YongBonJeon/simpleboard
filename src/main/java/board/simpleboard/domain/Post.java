package board.simpleboard.domain;

import lombok.Data;

@Data
public class Post {
    private Long postId;
    private String title;
    private String content;
    private Long userId;
}
