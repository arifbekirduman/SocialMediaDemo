package com.duman.MySocialMedia.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "post_id")
    private UUID postId;

    @Column(name = "comment_created_at")
    private Timestamp commentCreatedAt;


    @Column(name = "comment_message")
    private String commentMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    public Comment(long id, UUID postId, long commentId, String comment) {
        this.id = id;
        this.postId = postId;
        this.commentMessage = comment;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public Timestamp getCommentCreatedAt() {
        return commentCreatedAt;
    }

    public void setCommentCreatedAt(Timestamp postCreatedAt) {
        this.commentCreatedAt = postCreatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String comment) {
        this.commentMessage = comment;
    }
}
