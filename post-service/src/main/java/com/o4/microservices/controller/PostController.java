package com.o4.microservices.controller;

import com.o4.microservices.dto.BasicPost;
import com.o4.microservices.dto.Post;
import com.o4.microservices.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public Post create(@RequestBody BasicPost post) {
        return service.save(post);
    }

    @GetMapping
    public List<Post> list() {
        return service.list();
    }
}
