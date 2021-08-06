package com.duman.MySocialMedia.dataAccess;

import com.duman.MySocialMedia.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentDao extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(UUID postId);
    Comment findTop1ByPostIdOrderByCommentCreatedAtDesc(UUID postId);
}
