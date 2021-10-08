package org.eunu.board.controller;

import java.util.Optional;
import org.eunu.board.model.Post;
import org.eunu.board.post.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private Logger logger = LoggerFactory.getLogger(PostController.class);
    private final BoardService boardService;

    public PostController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/mainboard")
    public String mainPage(Model model) {
        var posts = boardService.postList();
        model.addAttribute("posts", posts);
        return "mainboard";
    }

    @GetMapping("post-new")
    public String newPost() {
        return "post-new";
    }

    @PostMapping("/mainboard")
    public String newPost(CreatePostRequest createPostRequest) {
        boardService.newPost(
            createPostRequest.title(),
            createPostRequest.content(),
            createPostRequest.userId()
        );
        logger.debug("new Post" + createPostRequest.title());
        return "redirect:/mainboard";
    }

    @GetMapping("/post-describe")
    public String selectPost(@RequestParam("postId") Integer postId, Model model) {
        logger.debug("get describe for "+postId.toString());
        Optional<Post> optionalPost = boardService.selectPost(postId);
        if(optionalPost.isEmpty()) {
            throw new RuntimeException();
        }
        boardService.hitUpdate(postId);
        model.addAttribute("post", optionalPost.get());
        return "/post-describe";
    }

    @GetMapping("/post-delete")
    public String removePost(@RequestParam("postId") Integer postId, Model model) {
        logger.debug("remove for "+postId.toString());
        Optional<Post> optionalPost = boardService.selectPost(postId);
        if(optionalPost.isEmpty()) {
            throw new RuntimeException();
        }
        boardService.removePost(optionalPost.get().getPostId());

        return "redirect:/mainboard";
    }

}
