package com.example.mysocialmedia.Model.Post;

import com.example.mysocialmedia.Model.Comment.CommentResponse;
import com.example.mysocialmedia.Model.User.UserResponse;

import java.util.UUID;

public class PostResponse {
    private UUID id;
    private String message;
    private String createdAt;
    private UserResponse user;
    private CommentResponse lastComment;

    public UUID getId() {
        return id;
    }

    public CommentResponse getLastComment() {
        return lastComment;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public UserResponse getUser() {
        return user;
    }
}
