package board.simpleboard.controller;

import board.simpleboard.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                            Model model){
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("loginMember", loginMember);
        return "loginHome";
    }

}
