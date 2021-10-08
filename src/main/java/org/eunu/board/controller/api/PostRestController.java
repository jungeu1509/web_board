package org.eunu.board.controller.api;

import java.util.Optional;
import org.eunu.board.model.Post;
import org.eunu.board.post.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostRestController {
    private final Logger logger = LoggerFactory.getLogger(PostRestController.class);
    private final BoardService boardService;

    public PostRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/api/v1/post-describe")
    public Post SelectPost(@RequestParam("postId") Integer postId, Model model) {
        logger.debug("get rest describe for "+postId.toString());
        Optional<Post> optionalPost = boardService.selectPost(postId);
        if(optionalPost.isEmpty()) {
            throw new RuntimeException();
        }
        return optionalPost.get();
    }


}
