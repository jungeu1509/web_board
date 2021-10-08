use board_mgmt;

CREATE TABLE users
(
    `user_id`        INT            NOT NULL    AUTO_INCREMENT COMMENT 'userId',
    `email`          VARCHAR(45)    NOT NULL    COMMENT 'Email',
    `user_name`      VARCHAR(45)    NOT NULL    COMMENT 'userName',
    `create_at`      DATETIME       NOT NULL    COMMENT 'createAt',
    `last_login_at`  DATETIME       NOT NULL    COMMENT 'lastLoginAt',
    `grade`          INT            NOT NULL    COMMENT 'grade',
    CONSTRAINT PK_users PRIMARY KEY (user_id, email)
);

ALTER TABLE users COMMENT 'users';

CREATE TABLE posts
(
    `post_id`    INT              NOT NULL    AUTO_INCREMENT COMMENT 'postId',
    `title`      VARCHAR(45)      NOT NULL    COMMENT 'title',
    `content`    VARCHAR(5000)    NULL        COMMENT 'content',
    `create_at`  DATETIME         NOT NULL    COMMENT 'createAt',
    `update_at`  DATETIME         NULL        COMMENT 'updateAt',
    `delete_at`  DATETIME         NULL        COMMENT 'deleteAt',
    `user_id`    INT              NOT NULL    COMMENT 'userId',
    `hit`        INT              NOT NULL    COMMENT 'hit',
    CONSTRAINT PK_posts PRIMARY KEY (post_id)
);

ALTER TABLE posts COMMENT 'posts';


ALTER TABLE posts
    ADD CONSTRAINT FK_posts_user_id_users_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE RESTRICT ON UPDATE RESTRICT;