package board.simpleboard.domain.form;

import lombok.Data;

@Data
public class LoginForm {
    String loginId;
    String password;

    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
