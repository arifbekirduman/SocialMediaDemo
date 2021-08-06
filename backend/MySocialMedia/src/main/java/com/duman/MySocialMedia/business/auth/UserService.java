package com.duman.MySocialMedia.business.auth;

import com.duman.MySocialMedia.dto.user.UserDto;
import com.duman.MySocialMedia.dto.user.UserLoginDto;
import com.duman.MySocialMedia.dto.user.UserRegisterDto;
import com.duman.MySocialMedia.dto.user.UserResponseDto;
import com.duman.MySocialMedia.entities.User;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);
    UserResponseDto loginUser(UserLoginDto userLoginDto);
    boolean isEmailInUse(String email);
    User getUserById(long userId);
    UserDto getUserByIdResultUserDto(long userId);
}
