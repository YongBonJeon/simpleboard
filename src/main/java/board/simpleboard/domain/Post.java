package board.simpleboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Post {
    @Id @GeneratedValue
    private Long postId;
    private String title;
    private String content;
    private Long userId;
}
