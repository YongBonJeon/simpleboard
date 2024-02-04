package board.simpleboard.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@Entity
public class Member extends DateEntity{

    @Id @GeneratedValue
    private Long Id;

    private String name;
    private String loginId;
    private String password;
    private String email;
    private String phoneNumber;
    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member() {
    }
    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
