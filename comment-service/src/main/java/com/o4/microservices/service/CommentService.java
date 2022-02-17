package com.o4.microservices.service;

import com.o4.microservices.api.RestClient;
import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.dto.comments.BasicComment;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.dto.comments.CommentStatus;
import com.o4.microservices.util.IDUtils;
import com.o4.microservices.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CommentService {

    private final RestClient restClient;
    private final Map<String, List<Comment>> comments = new HashMap<>();

    public CommentService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Comment save(String postId, BasicComment dto) {
        Comment comment = new Comment();
        comment.setId(IDUtils.nextID());
        comment.setTitle(dto.getTitle());
        comment.setPostId(postId);
        comment.setStatus(CommentStatus.PENDING);

        comments.putIfAbsent(postId, new ArrayList<>());
        comments.get(postId).add(comment);

        restClient.publish(new BusEvent(EventType.COMMENT_CREATED, comment));

        return comment;
    }

    public void update(Comment comment) {
        Optional<Comment> found = comments.get(comment.getPostId()).stream()
                .filter(c -> c.getId().equals(comment.getId()))
                .findFirst();

        if (found.isPresent()) {
            Comment existing = found.get();
            existing.setStatus(comment.getStatus());
            existing.setTitle(comment.getTitle());

            restClient.publish(new BusEvent(EventType.COMMENT_UPDATED, existing));
        } else {
            log.warn("Comment not found, post:{} and comment:{}", comment.getPostId(), comment.getId());
        }
    }

    public List<Comment> list(String postId) {
        return comments.get(postId);
    }

    public void handleEvent(BusEvent event) {
        if (EventType.COMMENT_MODERATED == event.getType()) {
            Comment comment = JsonUtils.convert(event.getData(), Comment.class);
            update(comment);
        } else {
            log.warn("Ignoring event {}", event.getType());
        }
    }
}
