package com.duman.MySocialMedia.api.controllers;

import com.duman.MySocialMedia.business.CommentService;
import com.duman.MySocialMedia.dto.comment.CommentDto;
import com.duman.MySocialMedia.dto.comment.CommentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/comment")
public class  CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getall-comment")
    public List<CommentDto> getAllComments(@RequestParam UUID postId){
        return commentService.getAllComment(postId);
    }


    @PostMapping("/create-comment/{id}")
    public String createComment(@PathVariable Long id ,@RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(id ,commentRequestDto);
    }
}
