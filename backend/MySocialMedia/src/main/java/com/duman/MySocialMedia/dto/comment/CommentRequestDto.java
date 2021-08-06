package com.duman.MySocialMedia.dto.comment;

import java.sql.Timestamp;
import java.util.UUID;

public class CommentRequestDto {
    private String commentMessage;
    private Timestamp commentCreatedAt;
    private UUID postId;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String commentMessage, Timestamp commentCreatedAt, UUID postId) {
        this.commentMessage = commentMessage;
        this.commentCreatedAt = commentCreatedAt;
        this.postId = postId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Timestamp getCommentCreatedAt() {
        return commentCreatedAt;
    }

    public void setCommentCreatedAt(Timestamp commentCreatedAt) {
        this.commentCreatedAt = commentCreatedAt;
    }
}
