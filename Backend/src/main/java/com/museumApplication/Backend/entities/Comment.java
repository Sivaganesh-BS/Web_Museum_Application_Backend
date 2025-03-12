package com.museumApplication.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "comment_id")
public class Comment {

//    comment_id (PK)
//    post_id (FK) ➡️ CommunityPost
//    user_id (FK) ➡️ User
//    content

    public UUID getComment_id() {
        return comment_id;
    }

    public void setComment_id(UUID comment_id) {
        this.comment_id = comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
//    created_at

    @Id
    @GeneratedValue
    private UUID comment_id;
    private String content;
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private CommunityPost communityPost;

    public CommunityPost getCommunityPost() {
        return communityPost;
    }

    public void setCommunityPost(CommunityPost communityPost) {
        this.communityPost = communityPost;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Comment(Users user, String content, CommunityPost communityPost) {
        this.user = user;
        this.content = content;
        this.communityPost = communityPost;
        this.created_at = new Date(System.currentTimeMillis());
        this.comment_id = UUID.randomUUID();
    }

    public Comment(){
        this.comment_id = UUID.randomUUID();
    }
}
