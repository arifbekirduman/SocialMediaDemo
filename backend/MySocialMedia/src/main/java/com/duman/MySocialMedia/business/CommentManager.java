package com.duman.MySocialMedia.business;

import com.duman.MySocialMedia.business.auth.UserService;
import com.duman.MySocialMedia.dataAccess.CommentDao;
import com.duman.MySocialMedia.dto.comment.CommentDto;
import com.duman.MySocialMedia.dto.comment.CommentRequestDto;
import com.duman.MySocialMedia.entities.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentManager implements CommentService {

    private CommentDao commentDao;
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public CommentManager(CommentDao commentDao, ModelMapper modelMapper,UserService userService) {
        this.commentDao = commentDao;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<CommentDto> getAllComment(UUID postId) {
        return mappingCommentDto(commentDao.findAllByPostId(postId));
    }

    @Override
    public CommentDto getLastComment(UUID postId) {
        Comment comment = commentDao.findTop1ByPostIdOrderByCommentCreatedAtDesc(postId);
        if (comment != null) {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            return commentDto;
        }
        else {
            return null;
        }


    }

    @Override
    public String createComment(Long id, CommentRequestDto commentRequestDto) {
        try {
            Comment comment = modelMapper.map(commentRequestDto , Comment.class);
            comment.setUser(userService.getUserById(id));
            Comment createComment = commentDao.save(comment);

            if (createComment != null){
                return "Comment created";
            }else
                return "comment could not be created";
        }
        catch (Exception e){
            System.out.println("catch create comment -> " + e.getMessage());
            return e.getMessage();
        }
    }

    private List<CommentDto> mappingCommentDto(List<Comment> commentList){
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment comment : commentList){
            commentDtoList.add(modelMapper.map(comment,CommentDto.class));
        }
        return commentDtoList;
    }

}
