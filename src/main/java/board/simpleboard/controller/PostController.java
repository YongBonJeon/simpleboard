package board.simpleboard.controller;

import board.simpleboard.domain.Comment;
import board.simpleboard.domain.Member;
import board.simpleboard.domain.Post;
import board.simpleboard.service.CommentService;
import board.simpleboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public String posts(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                        Model model) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("loginMember", loginMember);
        return "/post/posts";
    }

    @GetMapping("/{postId}")
    public String post(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                       @PathVariable long postId, Model model) {
        Post post = postService.findById(postId).get();
        log.info("post: {}", post);
        model.addAttribute("post", post);
        List<Comment> comments = commentService.findAllByPostId(postId);
        if(!comments.isEmpty())
            model.addAttribute("comments", comments);
        for (Comment comment : comments) {
            System.out.println(comment.getContent());
        }
        model.addAttribute("loginMember", loginMember);
        return "/post/post";
    }

    @GetMapping("/add")
    public String addForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model) {
        model.addAttribute("loginMember", loginMember);
        return "/post/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        log.info("post: {}", post);
        Post savedPost = postService.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        log.info("post: {}", post);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        Post post = postService.findById(postId).get();
        postService.delete(post);
        return "redirect:/posts";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                           @PathVariable Long postId, Model model) {
        Post post = postService.findById(postId).get();
        model.addAttribute("post", post);
        model.addAttribute("loginMember", loginMember);
        return "post/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, @ModelAttribute Post post) {
        Post updatePost = postService.findById(postId).get();
        updatePost.setTitle(post.getTitle());
        updatePost.setContent(post.getContent());
        return "redirect:/posts/{postId}";
    }
}
