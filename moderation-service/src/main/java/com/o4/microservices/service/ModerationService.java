package com.o4.microservices.service;

import com.o4.microservices.api.RestClient;
import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.dto.comments.CommentStatus;
import com.o4.microservices.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModerationService {

    private final RestClient busApi;

    public ModerationService(RestClient busApi) {
        this.busApi = busApi;
    }

    public void moderate(Comment comment) {

        if (comment.getTitle().contains("Billo")) {
            comment.setStatus(CommentStatus.REJECTED);
        } else {
            comment.setStatus(CommentStatus.APPROVED);
        }

        busApi.publish(new BusEvent(EventType.COMMENT_MODERATED, comment));
    }

    public void handleEvent(BusEvent event) {
        if (EventType.COMMENT_CREATED == event.getType()) {
            Comment comment = JsonUtils.convert(event.getData(), Comment.class);
            moderate(comment);
        } else {
            log.warn("[MS] Ignoring event: {}", event.getType());
        }
    }
}
