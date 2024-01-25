package board.simpleboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Member {

    @Id @GeneratedValue
    private Long Id;

    private String loginId;
    private String name;
    private String password;

    public Member() {
    }
    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
