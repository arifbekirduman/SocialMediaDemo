package com.duman.MySocialMedia.dto.post;

import java.sql.Timestamp;

public class PostDto {
    private String message;
    private Timestamp createdAt;

    public PostDto() {
    }

    public PostDto(String message, Timestamp createdAt) {
        this.message = message;
        this.createdAt = createdAt;
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
}
