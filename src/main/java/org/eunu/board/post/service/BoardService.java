package org.eunu.board.post.service;

import java.util.List;
import java.util.Optional;
import org.eunu.board.model.Post;

public interface BoardService {
    Optional<Post> newPost(String title, String content, Integer userId);

    // delete post from repository
    void deletePost(Integer postId);

    // remove post for users (remain repository)
    Optional<Post> removePost(Integer postId);

    Optional<Post> updatePost(Post post, String title, String content);

    List<Post> postList();

    Optional<Post> selectPost(Integer postId);

    Integer hitUpdate(Integer postId);
}
