package com.o4.microservices.controller;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/bus-event")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public String onEvent(@RequestBody BusEvent event) {
        service.handleEvent(event);

        return "EventHub - Received:" + event.getEvent();
    }
}
