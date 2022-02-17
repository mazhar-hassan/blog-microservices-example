package com.o4.microservices.service;

import com.o4.microservices.api.EventBusApi;
import com.o4.microservices.dto.BasicPost;
import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.Post;
import com.o4.microservices.util.IDUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    private final EventBusApi busApi;

    private final Map<String, Post> posts = new HashMap<>();

    public PostService(EventBusApi busApi) {
        this.busApi = busApi;
    }

    public Post save(BasicPost dto) {
        Post post = new Post();
        post.setId(IDUtils.nextID());
        post.setTitle(dto.getTitle());

        posts.put(post.getId(), post);
        busApi.publish(new BusEvent("POST_CREATED", post));

        return post;
    }

    public List<Post> list() {
        return new ArrayList<>(posts.values());
    }
}
