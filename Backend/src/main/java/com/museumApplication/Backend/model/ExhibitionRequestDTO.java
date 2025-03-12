package com.museumApplication.Backend.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ExhibitionRequestDTO {

    private String title;
    private String category;
    private String description;
    private String image;
    private UUID exhibition_id=null;

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


}
