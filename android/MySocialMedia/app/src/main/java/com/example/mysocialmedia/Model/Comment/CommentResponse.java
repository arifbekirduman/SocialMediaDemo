package com.example.mysocialmedia.Model.Comment;

import com.example.mysocialmedia.Model.User.UserResponse;

public class CommentResponse {
    private String commentCreatedAt;
    private String commentMessage;
    private UserResponse user;


    public String getCommentCreatedAt() {
        return commentCreatedAt;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public UserResponse getUser() {
        return user;
    }
}
