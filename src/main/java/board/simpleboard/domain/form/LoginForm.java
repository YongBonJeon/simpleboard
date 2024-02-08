package board.simpleboard.domain.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password;

    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
