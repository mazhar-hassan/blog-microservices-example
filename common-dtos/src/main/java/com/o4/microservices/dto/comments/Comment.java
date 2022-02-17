package com.o4.microservices.dto.comments;

public class Comment extends BasicComment {

    private String id;
    private CommentStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}