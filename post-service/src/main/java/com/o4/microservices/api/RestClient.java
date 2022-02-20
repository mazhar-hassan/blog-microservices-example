package com.o4.microservices.api;

import com.o4.microservices.dto.BusEvent;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestClient {

    @Value("${app.name}")
    private String appName;

    private final EventBusApi busApi;

    public RestClient(EventBusApi busApi) {
        this.busApi = busApi;
    }

    @Async
    public void publish(BusEvent event) {
        log.warn("Service:{} is now going to publish:{}", appName, event.getType());
        try {
            busApi.publish(event);
        } catch (RetryableException exception) {
            log.error("Unable to connect to publishing service, event-bus service may be down", exception);
        }
    }
}
