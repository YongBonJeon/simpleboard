package board.simpleboard.controller;

import board.simpleboard.domain.Member;
import board.simpleboard.domain.form.LoginForm;
import board.simpleboard.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return"login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm,
                        HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL) {
        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            return "login/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
