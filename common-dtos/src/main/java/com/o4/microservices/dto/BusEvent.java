package com.o4.microservices.dto;

public class BusEvent {

    private EventType type;
    private Object data;

    public BusEvent() {
    }

    public BusEvent(EventType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
