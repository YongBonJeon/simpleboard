package board.simpleboard.controller;

import board.simpleboard.domain.Comment;
import board.simpleboard.domain.Member;
import board.simpleboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("{postId}/add")
    public String addComment(@PathVariable Long postId, @RequestBody String content) {
        commentService.save(new Comment(content.split("=")[1], postId));
        return "redirect:/posts/{postId}";
    }

    @GetMapping("{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId, RedirectAttributes redirectAttributes) {
        Long postId = commentService.findById(commentId).get().getPostId();
        commentService.delete(commentService.findById(commentId).get());
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("{commentId}/edit")
    public String editForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                           @PathVariable Long commentId, Model model) {
        Comment comment = commentService.findById(commentId).get();
        model.addAttribute("comment", comment);
        model.addAttribute("postId", comment.getPostId());
        model.addAttribute("loginMember", loginMember);
        return "/comment/editForm";
    }

    @PostMapping("{commentId}/edit")
    public String editForm(@PathVariable Long commentId, @RequestBody String content, RedirectAttributes redirectAttributes) {
        Comment comment = commentService.findById(commentId).get();
        comment.setContent(content.split("=")[1]);
        commentService.update(comment);
        Long postId = comment.getPostId();
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }



}
