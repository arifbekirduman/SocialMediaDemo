package com.example.mysocialmedia.Model.User;

import com.google.gson.annotations.SerializedName;

public class UserRegister {
    @SerializedName("userName")
    private String userNick;

    @SerializedName("firstName")
    private String firstName;
    
    @SerializedName("lastName")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public UserRegister() {
    }

    public UserRegister(String userNick, String firstName, String lastName, String email, String password) {
        this.userNick = userNick;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
