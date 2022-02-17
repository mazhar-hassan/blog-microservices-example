package com.o4.microservices.controller;

import com.o4.microservices.dto.BusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/public/bus-event")
public class EventListenerController {

    @PostMapping
    public String onEvent(@RequestBody BusEvent event) {
        log.info("Event received: {}", event.getEvent());

        return "Received:" + event.getEvent();
    }
}
