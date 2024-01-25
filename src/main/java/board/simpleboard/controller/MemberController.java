package board.simpleboard.controller;

import board.simpleboard.domain.Member;
import board.simpleboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/members/add")
    public String addMember(@ModelAttribute("member") Member member) {
        return "member/addForm";
    }

    @PostMapping("/members/add")
    public String save(@ModelAttribute Member member) {
        System.out.println(member);
        memberService.save(member);
        return "redirect:/";
    }
}
