package org.eunu.board.post.repository;

import static org.eunu.board.JdbcUtils.toLocalDateTime;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.eunu.board.model.Post;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PostsJdbcRepository implements PostsRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostsJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Post> findById(Integer postId) {
        try {
            return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                    "SELECT * FROM posts WHERE post_id = :postId AND delete_at IS NULL ",
                    Collections.singletonMap("postId", postId),
                    postRowMapper)
            );
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from posts WHERE delete_at IS NULL", postRowMapper);
    }

    @Override
    @Transactional
    public Optional<Post> insert(Post post) {
        var update = jdbcTemplate.update(
            "INSERT INTO posts(title, content, create_at, update_at, delete_at, user_id, hit)" +
                " VALUES (:title, :content, :createAt, :updateAt, :deleteAt, :userId, :hit)",
            toParamMap(post));
        if (update != 1) { throw new RuntimeException("Nothing was inserted"); }

        return Optional.of(post);
    }

    @Override
    @Transactional
    public Optional<Post> update(Post post) {
        var update = jdbcTemplate.update("UPDATE posts SET title = :title, content = :content, update_at = :updateAt, delete_at = :deleteAt, hit = :hit " +
                "WHERE post_id = :postId AND delete_at IS NULL ",
            toParamMap(post)
        );
        if(update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return Optional.of(post);
    }

    @Override
    public void deletePostById(Integer postId) {
        jdbcTemplate.update("DELETE FROM posts WHERE post_id = :postId",
            Collections.emptyMap());
    }

    @Override
    @Transactional
    public void updateHit(Integer postId) {
        Post post;

        if(findById(postId).isEmpty()) {return;}
        else {post = findById(postId).get();}

        var update = jdbcTemplate.update("UPDATE posts SET hit = " +
            (post.getHit()+1) +
                " WHERE post_id = :postId",
            toParamMap(post)
        );
        if(update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
    }

    private static final RowMapper<Post> postRowMapper = (resultSet, i) -> {
        var postId = resultSet.getInt("post_id");
        var title = resultSet.getString("title");
        var content = resultSet.getString("content");
        var createAt = toLocalDateTime(resultSet.getTimestamp("create_at"));
        var updateAt = toLocalDateTime(resultSet.getTimestamp("update_at"));
        var deleteAt = toLocalDateTime(resultSet.getTimestamp("delete_at"));
        var userId = resultSet.getInt("user_id");
        var hit = resultSet.getInt("hit");
        return new Post(
            postId,
            title,
            content,
            createAt,
            updateAt,
            deleteAt,
            userId,
            hit
        );
    };

    private Map<String, Object> toParamMap(Post post) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("postId", post.getPostId());
        paramMap.put("title", post.getTitle());
        paramMap.put("content", post.getContent());
        paramMap.put("createAt", post.getCreatedAt());
        paramMap.put("updateAt", post.getUpdatedAt());
        paramMap.put("deleteAt", post.getDeletedAt());
        paramMap.put("userId", post.getUserId());
        paramMap.put("hit", post.getHit());
        return paramMap;
    }
}
