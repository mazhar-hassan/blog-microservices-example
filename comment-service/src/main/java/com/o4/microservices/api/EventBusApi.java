package com.o4.microservices.api;

import com.o4.microservices.dto.BusEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "event-bus", url = "http://eventbus-srv:8085")
public interface EventBusApi {

    @PostMapping("/public/bus-event")
    void publish(BusEvent event);
}
