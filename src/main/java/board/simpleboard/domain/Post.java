package board.simpleboard.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@Entity
public class Post extends DateEntity{
    @Id @GeneratedValue
    private Long Id;
    private String title;
    private String content;

    @CreatedDate
    private LocalDate createdDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Post() {
    }
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
