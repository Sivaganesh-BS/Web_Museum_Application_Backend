package com.museumApplication.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "exhibition_id")
public class Exhibition {

//    exhibition_id (PK)
//    title
//    category
//    description
//    image_url
//    created_date
//    updated_date
    @Id
    @GeneratedValue
    @Setter
    @Getter
    private UUID exhibition_id;
    private String title;
    private String category;
    private String description;

    private String image;
    private Date created_date;
    private Date updated_date;

    public UUID getExhibition_id() {
        return exhibition_id;
    }

    public void setExhibition_id(UUID exhibition_id) {
        this.exhibition_id = exhibition_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public List<CommunityPost> getCommunityPosts() {
        return communityPosts;
    }

    public void setCommunityPosts(List<CommunityPost> communityPosts) {
        this.communityPosts = communityPosts;
    }


    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL)
    private List<CommunityPost> communityPosts;

    public Exhibition(){
        this.exhibition_id = UUID.randomUUID();
    }
}
