package board.simpleboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comment {

    @Id @GeneratedValue
    private Long Id;
    private String content;

    private Long postId;

    public Comment() {
    }

    public Comment(String content, Long postId) {
        this.content = content;
        this.postId = postId;
    }
}
