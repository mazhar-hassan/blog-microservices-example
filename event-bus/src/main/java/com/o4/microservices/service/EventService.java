package com.o4.microservices.service;

import com.o4.microservices.dto.BusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EventService {

    private final EventHookSender sender;
    static Map<String, URI> subscribers = new HashMap<>();

    static {
        try {
            subscribers.put("post-service", new URI("http://localhost:8081"));
            subscribers.put("comment-service", new URI("http://localhost:8082"));
            subscribers.put("query-service", new URI("http://localhost:8083"));
            subscribers.put("moderation-service", new URI("http://localhost:8084"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public EventService(EventHookSender sender) {
        this.sender = sender;
    }

    public void handleEvent(BusEvent event) {
        for (var entry : subscribers.entrySet()) {
            log.info("Publishing event to service:{} with data:{}", entry.getKey(), event);
            try {
                String response = sender.send(entry.getValue(), event);
                log.info("Response received: {}", response);
            } catch (Exception e) {
                log.error("Failed to publish event: {}", e.getMessage());
            }
        }
    }
}
