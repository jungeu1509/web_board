package org.eunu.board.post.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.eunu.board.model.Post;
import org.eunu.board.post.repository.PostsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultBoardService implements BoardService {
    private final Logger logger = LoggerFactory.getLogger(DefaultBoardService.class);

    private final PostsRepository postsRepository;

    public DefaultBoardService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Optional<Post> newPost(String title, String content, Integer userId) {
        Post post = new Post(
            title,
            content,
            LocalDateTime.now(),
            userId,
            0
        );
        logger.debug("new post generate : "+title);
        return postsRepository.insert(post);
    }

    @Override
    public void deletePost(Integer postId) {
        logger.warn("erase post");
        postsRepository.deletePostById(postId);
    }

    @Override
    public Optional<Post> removePost(Integer postId) {
        Optional<Post> optionalPost = postsRepository.findById(postId);
        if(optionalPost.isEmpty()) {
            return Optional.empty();
        }
        Post post = optionalPost.get();
        post.setDeleteAt();
        return postsRepository.update(post);
    }

    @Override
    public Optional<Post> updatePost(Post post, String title, String content) {
        post.updatePost(title,content);
        return postsRepository.update(post);
    }

    @Override
    public List<Post> postList() {
        return postsRepository.findAll();
    }

    @Override
    public Optional<Post> selectPost(Integer postId) {
        return postsRepository.findById(postId);
    }

    @Override
    public Integer hitUpdate(Integer postId) {
        Optional<Post> optionalPost = postsRepository.findById(postId);
        Integer ret = 0;
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            ret = post.updateHit();
            updatePost(post, post.getTitle(), post.getContent());
        }
        return ret;
    }
}
