package com.duman.MySocialMedia.api.controllers;

import com.duman.MySocialMedia.business.PostService;
import com.duman.MySocialMedia.dto.post.PostDto;
import com.duman.MySocialMedia.dto.post.PostRequestPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getall")
    public List<PostRequestPost> findAllByOrderByCreatedAt(){
        return postService.getPostAll();
    }

    @PostMapping("/create/{id}")
    public String sendPost(@PathVariable Long id, @RequestBody PostDto postDto){
        return postService.sendPost(id ,postDto);
    }
}
