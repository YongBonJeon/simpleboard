package board.simpleboard.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@Entity
public class Post extends DateEntity{
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long Id;
    private String title;
    private String content;

    private String boardName;

    @CreatedDate
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    public Post() {
    }
    public Post(String title, String content, String boardName) {
        this.title = title;
        this.content = content;
        this.boardName = boardName;
    }

    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;

    }
}
