package org.eunu.board.post.repository;

import java.util.List;
import java.util.Optional;
import org.eunu.board.model.Post;

public interface PostsRepository {
    Optional<Post> findById(Integer postId);

    List<Post> findAll();

    Optional<Post> insert(Post post);

    Optional<Post> update(Post post);

    void deletePostById(Integer postId);

    void updateHit(Integer postId);
}
