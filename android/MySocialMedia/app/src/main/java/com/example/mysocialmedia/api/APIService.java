package com.example.mysocialmedia.api;

import com.example.mysocialmedia.Model.Comment.CommentRequest;
import com.example.mysocialmedia.Model.Comment.CommentResponse;
import com.example.mysocialmedia.Model.Post.PostRequest;
import com.example.mysocialmedia.Model.Post.PostResponse;
import com.example.mysocialmedia.Model.User.User;
import com.example.mysocialmedia.Model.User.UserLogin;
import com.example.mysocialmedia.Model.User.UserRegister;
import com.example.mysocialmedia.Model.User.UserResponse;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @POST("api/users/register-user")
    Call<String> userRegister (@Body UserRegister userRegister);

    @POST("api/users/login-user")
    Call<User> userLogin(@Body UserLogin userLogin);

    @GET("api/users/get-user")
    Call<UserResponse> getUser(@Query("userId") Long userId);

    @GET("api/posts/getall")
    Call<List<PostResponse>> getAllPost();

    @POST("api/posts/create/{id}")
    Call<String> sendPost(@Path("id")Long id, @Body PostRequest postRequest);

    @GET("api/comment/getall-comment")
    Call<List<CommentResponse>> getAllComments(@Query("postId") UUID postId);

    @POST("/api/comment/create-comment/{id}")
    Call<String> sendComment(@Path("id") Long id, @Body CommentRequest commentRequest);


}
