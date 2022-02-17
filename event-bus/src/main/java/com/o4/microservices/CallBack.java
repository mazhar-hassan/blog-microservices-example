package com.o4.microservices;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.service.EventHookSender;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;

@Slf4j
public class CallBack implements Runnable {

    private final String serviceName;
    private final EventHookSender sender;
    private final URI uri;

    private BusEvent event;

    public CallBack(String serviceName, EventHookSender sender, URI uri) {
        this.serviceName = serviceName;
        this.sender = sender;
        this.uri = uri;
    }

    public BusEvent getEvent() {
        return event;
    }

    public void setEvent(BusEvent event) {
        this.event = event;
    }

    public void send() {
        log.info("Callback to service:{} with type:{}", serviceName, event.getType());
        try {
            String response = sender.send(uri, event);
            log.info("Response received: {}", response);
        } catch (RetryableException e) {
            log.error("Connection refused: {} Service may be down", serviceName);
        } catch (Exception e) {
            log.error("Failed to publish event", e);
        }
    }

    @Override
    public void run() {
        send();
    }
}
