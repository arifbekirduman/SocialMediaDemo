package com.duman.MySocialMedia.dto.post;

import com.duman.MySocialMedia.dto.comment.CommentDto;
import com.duman.MySocialMedia.dto.user.UserDto;

import java.sql.Timestamp;
import java.util.UUID;

public class PostRequestPost {
    private UUID id;
    private String message;
    private Timestamp createdAt;
    private UserDto user;
    private CommentDto lastComment;

    public PostRequestPost() {
    }

    public PostRequestPost(UUID id, String message, Timestamp createdAt, UserDto user) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
    }

    public CommentDto getLastComment() {
        return lastComment;
    }

    public void setLastComment(CommentDto lastComment) {
        this.lastComment = lastComment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
