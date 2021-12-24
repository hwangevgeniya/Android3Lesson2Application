package com.geektech.android3lesson2application.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    int id;
    @SerializedName("user")
    int userId;
    @SerializedName("group")
    int groupId;
    String title, content;

    public Post(int userId, int groupId, String title, String content) {
        this.userId = userId;
        this.groupId = groupId;
        this.title = title;
        this.content = content;
    }

    public Post(int userId){
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", groupId=" + groupId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }


}
