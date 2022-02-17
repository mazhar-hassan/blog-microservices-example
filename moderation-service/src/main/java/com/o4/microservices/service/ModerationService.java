package com.o4.microservices.service;

import com.o4.microservices.api.EventBusApi;
import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.dto.comments.CommentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ModerationService {

    private final EventBusApi busApi;

    public ModerationService(EventBusApi busApi) {
        this.busApi = busApi;
    }

    public void moderate(Comment comment) {

        if (comment.getTitle().contains("Billo")) {
            comment.setStatus(CommentStatus.REJECTED);
        } else {
            comment.setStatus(CommentStatus.APPROVED);
        }

        busApi.publish(new BusEvent("COMMENT_MODERATED", comment));
    }

}
