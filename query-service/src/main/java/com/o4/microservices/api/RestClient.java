package com.o4.microservices.api;

import com.o4.microservices.dto.BusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    public void publish(BusEvent event) {
        log.warn("Service:{} is now going to publish:{}", appName, event.getType());
        busApi.publish(event);
    }
}
