package board.simpleboard.controller;

import board.simpleboard.domain.Member;
import board.simpleboard.domain.Post;
import board.simpleboard.service.MemberService;
import board.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;


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

    @GetMapping("/member")
    public String member(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {
        Member member = memberService.findByLoginId(loginMember.getLoginId()).get();
        model.addAttribute("member", member);
        model.addAttribute("loginMember", loginMember);
        List<Post> posts = postService.findAllByMemberId(member);
        model.addAttribute("posts", posts);

        return "member/member";
    }
}
