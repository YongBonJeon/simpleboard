package board.simpleboard.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Data
@Entity
public class Member extends DateEntity{

    @Id @GeneratedValue
    @Column(name = "member_id")
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

    public Member(String name, String loginId, String password, String email, String phoneNumber, Address address, Role role) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
}
