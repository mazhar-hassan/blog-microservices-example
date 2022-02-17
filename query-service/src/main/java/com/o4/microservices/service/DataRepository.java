package com.o4.microservices.service;

import com.o4.microservices.dto.BlogPost;
import com.o4.microservices.dto.comments.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DataRepository {

    private Map<String, BlogPost> posts = new HashMap<>();

    public Map<String, BlogPost> getPosts() {
        return posts;
    }

    public void setPosts(Map<String, BlogPost> posts) {
        this.posts = posts;
    }

    public BlogPost findPost(String id) {
        return posts.get(id);
    }

    public void addPost(BlogPost post) {
        posts.put(post.getId(), post);

        for (Map.Entry<String, BlogPost> entry : posts.entrySet()) {
            log.debug("{} => {}", entry.getKey(), entry.getValue().getTitle());
        }
    }

    public void addComment(Comment comment) {
        posts.get(comment.getPostId())
                .getComments()
                .add(comment);
    }
}
