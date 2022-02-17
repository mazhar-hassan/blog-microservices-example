package com.o4.microservices.dto;

import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.dto.posts.Post;

import java.util.ArrayList;
import java.util.List;

public class BlogPost extends Post {

    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
