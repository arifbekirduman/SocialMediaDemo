package com.duman.MySocialMedia.business;

import com.duman.MySocialMedia.business.auth.UserService;
import com.duman.MySocialMedia.dataAccess.PostDao;
import com.duman.MySocialMedia.dto.comment.CommentDto;
import com.duman.MySocialMedia.dto.post.PostDto;
import com.duman.MySocialMedia.dto.post.PostRequestPost;
import com.duman.MySocialMedia.entities.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostManager implements PostService {
    private PostDao postDao;
    private UserService userService;
    private CommentService commentService;
    ModelMapper modelMapper;

    @Autowired
    public PostManager(PostDao postDao, ModelMapper modelMapper,UserService userService, CommentService commentService) {
        this.postDao = postDao;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Override
    public List<PostRequestPost> getPostAll() {
        return mappingPostDto(postDao.findAllByOrderByCreatedAtDesc());
    }

    @Override
    public String sendPost(Long id, PostDto postDto) {
        try {
            Post post = modelMapper.map(postDto, Post.class);
            post.setUser(userService.getUserById(id));
            Post save = postDao.save(post);
            if (save != null){
                return "Post created";
            }
            else {
                return "Post could not be created";
            }

        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    private CommentDto getLastComment(UUID postId){
        return commentService.getLastComment(postId);
    }


    private List<PostRequestPost> mappingPostDto(List<Post> posts) {
        List<PostRequestPost> postDtoList = new ArrayList<>();


        for (Post post : posts) {
            PostRequestPost postRequestPost = modelMapper.map(post, PostRequestPost.class);
            if (getLastComment(post.getId()) != null){
                postRequestPost.setLastComment(getLastComment(post.getId()));
            }
            postDtoList.add(postRequestPost);

        }

        return postDtoList;
    }


}
