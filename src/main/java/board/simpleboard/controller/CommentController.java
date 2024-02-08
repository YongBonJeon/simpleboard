package board.simpleboard.controller;

import board.simpleboard.domain.Comment;
import board.simpleboard.domain.Member;
import board.simpleboard.service.CommentService;
import board.simpleboard.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("{postId}/add")
    public String addComment(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                             @PathVariable Long postId, RedirectAttributes redirectAttributes,
                             @Valid @ModelAttribute Comment comment, BindingResult bindingResult) {
        //model.addAttribute("loginMember", loginMember);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("postId", postId);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/posts/{postId}";
        }
        commentService.save(new Comment(comment.getContent(), postService.findById(postId).get(), loginMember));
        return "redirect:/posts/{postId}";
    }

    @GetMapping("{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId, RedirectAttributes redirectAttributes) {
        Long postId = commentService.findById(commentId).get().getPost().getId();
        commentService.delete(commentService.findById(commentId).get());

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("{commentId}/edit")
    public String editForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                           @PathVariable Long commentId, Model model) {
        Comment comment = commentService.findById(commentId).get();
        model.addAttribute("comment", comment);
        model.addAttribute("postId", comment.getPost().getId());
        model.addAttribute("loginMember", loginMember);
        return "/comment/editForm";
    }

    @PostMapping("{commentId}/edit")
    public String editForm(@PathVariable Long commentId, @RequestBody String content, RedirectAttributes redirectAttributes) {
        commentService.update(commentId, content.split("=")[1]);
        Long postId = commentService.findById(commentId).get().getPost().getId();
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }



}
