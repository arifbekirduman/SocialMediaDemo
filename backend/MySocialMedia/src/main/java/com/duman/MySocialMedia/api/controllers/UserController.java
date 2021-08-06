package com.duman.MySocialMedia.api.controllers;

import com.duman.MySocialMedia.business.auth.UserService;
import com.duman.MySocialMedia.dto.user.UserDto;
import com.duman.MySocialMedia.dto.user.UserLoginDto;
import com.duman.MySocialMedia.dto.user.UserRegisterDto;
import com.duman.MySocialMedia.dto.user.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public String registerUser(@RequestBody UserRegisterDto userRegisterDto){
        return userService.registerUser(userRegisterDto);
    }

    @PostMapping("/login-user")
    public UserResponseDto loginUser(@RequestBody UserLoginDto userLoginDto){
        return userService.loginUser(userLoginDto);
    }

    @GetMapping("/get-user")
    public UserDto getUserById(@RequestParam Long userId){
        return userService.getUserByIdResultUserDto(userId);
    }
}
