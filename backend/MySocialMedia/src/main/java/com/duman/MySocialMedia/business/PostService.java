package com.duman.MySocialMedia.business;

import com.duman.MySocialMedia.dto.post.PostDto;
import com.duman.MySocialMedia.dto.post.PostRequestPost;

import java.util.List;

public interface PostService {
    List<PostRequestPost> getPostAll();
    String sendPost(Long id, PostDto postDto);
}
