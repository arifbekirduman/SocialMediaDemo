package com.example.mysocialmedia.Model.Post;

import java.sql.Date;
import java.sql.Timestamp;

public class PostRequest {
    private String message;
    private Timestamp createdAt;

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
}
