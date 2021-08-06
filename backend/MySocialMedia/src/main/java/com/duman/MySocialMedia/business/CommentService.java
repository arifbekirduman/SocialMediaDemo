package com.duman.MySocialMedia.business;

import com.duman.MySocialMedia.dto.comment.CommentDto;
import com.duman.MySocialMedia.dto.comment.CommentRequestDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentDto> getAllComment(UUID postId);
    CommentDto getLastComment(UUID postId);
    String createComment(Long id, CommentRequestDto commentRequestDto);
}
