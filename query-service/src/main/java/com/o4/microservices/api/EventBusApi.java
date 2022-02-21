package com.o4.microservices.api;

import com.o4.microservices.dto.BusEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "event-bus", url = "http://host.docker.internal:8085")
public interface EventBusApi {

    @PostMapping("/public/bus-event")
    void publish(BusEvent event);

    @GetMapping("/public/bus-event")
    List<BusEvent> getEvents();
}
