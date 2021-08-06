package com.duman.MySocialMedia.dataAccess.auth;

import com.duman.MySocialMedia.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email,String password);
    User findByUserId(Long userId);
}
