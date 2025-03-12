package com.museumApplication.Backend.model;

import java.util.UUID;

public class CommentRequestDTO {

    private String content;

    private Long userid;

    private UUID communitypostid;

    private UUID commentid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public UUID getCommunitypostid() {
        return communitypostid;
    }

    public void setCommunitypostid(UUID communitypostid) {
        this.communitypostid = communitypostid;
    }

    public UUID getCommentid() {
        return commentid;
    }

    public void setCommentid(UUID commentid) {
        this.commentid = commentid;
    }
}
