package com.o4.microservices.service.commands;

import com.o4.microservices.dto.BusEvent;
import com.o4.microservices.dto.EventType;
import com.o4.microservices.service.DataRepository;

public abstract class EventCommand {

    protected final DataRepository repository;

    protected EventCommand(DataRepository repository) {
        this.repository = repository;
    }

    public abstract void execute(BusEvent event);

    public abstract EventType getType();
}
