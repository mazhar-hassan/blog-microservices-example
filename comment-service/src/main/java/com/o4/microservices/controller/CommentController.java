package com.o4.microservices.controller;

import com.o4.microservices.dto.comments.BasicComment;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pc")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/{postId}/comments")
    public Comment create(@PathVariable("postId") String postId,
                          @RequestBody BasicComment comment) {
        return service.save(postId, comment);
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> list(@PathVariable("postId") String postId) {
        return service.list(postId);
    }
}
