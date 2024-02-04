package board.simpleboard.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Comment extends DateEntity {

    @Id @GeneratedValue
    private Long Id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Comment() {
    }

    public Comment(String content, Post post, Member member) {
        this.content = content;
        this.post = post;
        this.member = member;
    }
}
