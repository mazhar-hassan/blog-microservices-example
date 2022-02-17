package com.o4.microservices.service;

import com.o4.microservices.dto.BlogPost;
import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.service.commands.EventCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class QueryService {

    private static final Map<EventType, EventCommand> commands = new EnumMap<>(EventType.class);
    private final DataRepository dataRepository;

    public QueryService(List<EventCommand> eventCommands, DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        eventCommands.forEach(event -> commands.put(event.getType(), event));
    }

    public List<BlogPost> list() {
        return new ArrayList<>(dataRepository.getPosts().values());
    }

    public BlogPost findPostById(String postId) {
        return dataRepository.findPost(postId);
    }

    public void handleEvent(BusEvent event) {

        if (commands.containsKey(event.getType())) {
            EventCommand command = commands.get(event.getType());
            command.execute(event);
        } else {
            log.warn("QS: Skipped event {}", event.getType());
        }
    }

}
