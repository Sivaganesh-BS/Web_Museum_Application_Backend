package com.museumApplication.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "post_id")
@Table(name = "communitypost")
public class CommunityPost {
//    post_id (PK)
//    user_id (FK) ➡️ User
//    exhibition_id (FK) ➡️ Exhibition
//    content
//    created_at
//    updated_at

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    @Id
    @GeneratedValue
    private UUID post_id;
    private String content;
    private Date created_at;
    private Date updated_at;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.ALL)
    private List<Comment> comment;

    @ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
    private Exhibition exhibition;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UUID getPost_id() {
        return post_id;
    }

    public void setPost_id(UUID post_id) {
        this.post_id = post_id;
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

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public CommunityPost(Users user, String content, Exhibition exhibition) {
        this.user = user;
        this.content = content;
        this.exhibition = exhibition;
        this.created_at = this.created_at == null ? new Date(System.currentTimeMillis()) : this.created_at;
        this.updated_at = new Date(System.currentTimeMillis());

    }

    public CommunityPost(){
        this.post_id = UUID.randomUUID();
    }
}
