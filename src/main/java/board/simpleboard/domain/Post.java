package board.simpleboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Post {
    @Id @GeneratedValue
    private Long Id;
    private String title;
    private String content;

    public Post() {
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
