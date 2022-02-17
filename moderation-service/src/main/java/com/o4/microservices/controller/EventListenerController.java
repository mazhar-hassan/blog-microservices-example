package com.o4.microservices.controller;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.comments.Comment;
import com.o4.microservices.service.ModerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/bus-event")
public class EventListenerController {

    private final ModerationService service;

    public EventListenerController(ModerationService service) {
        this.service = service;
    }

    @PostMapping
    public String onEvent(@RequestBody BusEvent event) {
        log.info("Event received: {}", event.getEvent());
        if ("COMMENT_CREATED".equals(event.getEvent())) {
            service.moderate((Comment) event.getData());
        }
        return "Received:" + event.getEvent();
    }
}
