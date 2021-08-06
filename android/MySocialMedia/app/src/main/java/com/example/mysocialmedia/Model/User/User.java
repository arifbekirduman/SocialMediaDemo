package com.example.mysocialmedia.Model.User;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class User implements Serializable {
    private long userId;
    private String userNick;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
    }

    public User(long userId, String userNick, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.userNick = userNick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
