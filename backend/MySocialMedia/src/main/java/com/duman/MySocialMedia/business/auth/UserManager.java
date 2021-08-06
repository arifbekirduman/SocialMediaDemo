package com.duman.MySocialMedia.business.auth;

import com.duman.MySocialMedia.dataAccess.auth.UserDao;
import com.duman.MySocialMedia.dto.user.UserDto;
import com.duman.MySocialMedia.dto.user.UserLoginDto;
import com.duman.MySocialMedia.dto.user.UserRegisterDto;
import com.duman.MySocialMedia.dto.user.UserResponseDto;
import com.duman.MySocialMedia.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private ModelMapper modelMapper;

    @Autowired
    public UserManager(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        try {
            User user = modelMapper.map(userRegisterDto, User.class);

            if (isEmailInUse(user.getEmail())) {
                return "user_already_exists";
            }
            userDao.save(user);
            return "register_success";
        } catch (Exception e) {
            System.out.println(e.getCause());
            return "register_failure";
        }
    }

    @Override
    public UserResponseDto loginUser(UserLoginDto userLoginDto) {
        try {
            User user = modelMapper.map(userLoginDto, User.class);
            User loginUser = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());


            if (loginUser != null) {
                return modelMapper.map(loginUser, UserResponseDto.class);
            } else return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isEmailInUse(String email) {
        if (userDao.findByEmail(email) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserById(long userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public UserDto getUserByIdResultUserDto(long userId) {
        return modelMapper.map(userDao.findByUserId(userId), UserDto.class);
    }


}
