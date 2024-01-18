package board.simpleboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comment {

    @Id @GeneratedValue
    private Long commentId;
    private Long postId;
    private String content;
}
