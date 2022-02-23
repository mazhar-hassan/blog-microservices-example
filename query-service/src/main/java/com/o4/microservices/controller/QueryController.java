package com.o4.microservices.controller;

import com.o4.microservices.dto.BlogPost;
import com.o4.microservices.service.QueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/query")
public class QueryController {

    private final QueryService service;

    public QueryController(QueryService service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public List<BlogPost> list() {
        return service.list();
    }

    @GetMapping("/posts/{postId}")
    public BlogPost findPostById(@PathVariable("postId") String postId) {
        return service.findPostById(postId);
    }
}
