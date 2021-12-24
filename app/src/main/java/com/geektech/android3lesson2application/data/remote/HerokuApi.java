package com.geektech.android3lesson2application.data.remote;

import com.geektech.android3lesson2application.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HerokuApi {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @POST("/posts")
    Call<Post> createPost(
            @Body Post post
    );

    @PUT("/posts/{id}")
    Call<Post> updatePost(
            @Path("id") int id,
            @Body Post post
            //@Field("userId") int userId,
            //@Field("title") String title,
            //@Field("content") String content,
            //@Field("groupId") int groupId
    );

    @DELETE("/posts/{id}")
    Call<Post> deletePost(
            @Path("id") int id
    );

}
