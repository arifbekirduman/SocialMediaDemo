package com.duman.MySocialMedia.dto.comment;

import com.duman.MySocialMedia.dto.user.UserDto;

import java.sql.Timestamp;

public class CommentDto {
    private String commentMessage;
    private Timestamp commentCreatedAt;
    private UserDto user;

    public CommentDto() {
    }

    public CommentDto(String comment, Timestamp commentCreatedAt, UserDto user) {
        this.commentMessage = comment;
        this.commentCreatedAt = commentCreatedAt;
        this.user = user;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
