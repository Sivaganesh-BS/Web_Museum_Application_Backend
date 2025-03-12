package com.museumApplication.Backend.model;

import java.util.UUID;

public class CommunityPostRequestDTO {

    private String content;
    private Long userId;
    private UUID exhibitionId;
    private UUID community_post_id;

    public UUID getCommunity_post_id() {
        return community_post_id;
    }

    public void setCommunity_post_id(UUID community_post_id) {
        this.community_post_id = community_post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UUID getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(UUID exhibitionId) {
        this.exhibitionId = exhibitionId;
    }
}
