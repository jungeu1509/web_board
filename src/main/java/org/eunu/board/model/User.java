package org.eunu.board.model;

import java.time.LocalDateTime;

public class User {
    private Integer userId;
    private final String email;
    private String userName;
    private final LocalDateTime createAt;
    private LocalDateTime lastLoginAt;
    private Integer grade;

    public User(Integer userId, String email, String userName, LocalDateTime createAt,
        LocalDateTime lastLoginAt, Integer grade) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.createAt = createAt;
        this.lastLoginAt = lastLoginAt;
        this.grade = grade;
    }

    public User(String email, String userName, LocalDateTime createAt, Integer grade) {
        this.userId = 0;
        this.email = email;
        this.userName = userName;
        this.createAt = createAt;
        this.grade = grade;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public Integer getGrade() {
        return grade;
    }
}
