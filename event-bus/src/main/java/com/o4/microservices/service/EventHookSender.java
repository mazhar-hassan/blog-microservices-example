package com.o4.microservices.service;

import com.o4.microservices.dto.BusEvent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;

@FeignClient(name = "the-hook", url = "http://host.docker.internal:8081")
public interface EventHookSender {

    @PostMapping("/public/bus-event")
    String send(URI uri, BusEvent event);
}
