package com.duman.MySocialMedia.dataAccess;

import com.duman.MySocialMedia.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostDao extends JpaRepository<Post, UUID> {
    List<Post> findAllByOrderByCreatedAtDesc();
}
