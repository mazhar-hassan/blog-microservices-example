package com.o4.microservices.controller;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/bus-event")
public class EventListenerController {

    private final CommentService service;

    public EventListenerController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    public String onEvent(@RequestBody BusEvent event) {
        log.info("Event received: {}", event.getType());
        service.handleEvent(event);

        return "[RCV-CS] " + event.getType();
    }
}
