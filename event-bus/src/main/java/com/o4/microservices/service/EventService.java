package com.o4.microservices.service;

import com.o4.microservices.CallBack;
import com.o4.microservices.dto.BusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EventService {

    private final List<CallBack> callBacks = new ArrayList<>();
    private final List<BusEvent> events = new ArrayList<>();

    public EventService(EventHookSender sender) {

        try {
            callBacks.add(new CallBack("[query-service]", sender, new URI("http://localhost:8083")));
            callBacks.add(new CallBack("[post-service]", sender, new URI("http://localhost:8081")));
            callBacks.add(new CallBack("[comment-service]", sender, new URI("http://localhost:8082")));
            callBacks.add(new CallBack("[moderation-service]", sender, new URI("http://localhost:8084")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public List<BusEvent> getEvents() {
        return events;
    }

    public void handleEvent(BusEvent event) {

        //persist event
        events.add(event);

        //update callbacks
        callBacks.forEach(callBack -> {
            callBack.setEvent(event);
            new Thread(callBack).start();
        });
    }
}
