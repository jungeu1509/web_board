package org.eunu.board.model;

import java.time.LocalDateTime;

public class Post {
    private Integer postId;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private final Integer userId;
    private Integer hit;

    public Post(Integer postId, String title, String content, LocalDateTime createdAt,
        LocalDateTime updatedAt, LocalDateTime deleteAt, Integer userId, Integer hit) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deleteAt;
        this.userId = userId;
        this.hit = hit;
    }

    public Post(String title, String content, LocalDateTime createdAt,
        Integer userId, Integer hit) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
        this.hit = hit;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getHit() {
        return hit;
    }

    public Integer updateHit() {
        hit = hit+1;
        return hit;
    }

    public void setDeleteAt() {
        var now = LocalDateTime.now();
        this.updatedAt = now;
        this.deletedAt = now;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
        setUpdatedAt();
    }

    private void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

}
