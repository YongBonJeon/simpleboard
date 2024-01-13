package board.simpleboard.domain;

import lombok.Data;

@Data
public class Comment {

    private Long commentId;
    private Long postId;
    private String content;
}
