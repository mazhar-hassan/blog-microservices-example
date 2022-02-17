package com.o4.microservices.service;

import com.o4.microservices.api.EventBusApi;
import com.o4.microservices.dto.BusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ApplicationStartUp {

    private final EventBusApi eventBusApi;
    private final QueryService service;

    public ApplicationStartUp(EventBusApi eventBusApi, QueryService service) {
        this.eventBusApi = eventBusApi;
        this.service = service;
    }

    @EventListener(ApplicationReadyEvent.class)
    void initData() {
        log.info("Initializing data if any events");
        List<BusEvent> events = eventBusApi.getEvents();
        log.info("Total event received: {}", events.size());
        events.forEach(service::handleEvent);
        log.info("Import completed....");
    }
}
