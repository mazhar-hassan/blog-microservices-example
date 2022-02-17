package com.o4.microservices.dto;

public class BusEvent {
    private String event;
    private Object data;

    public BusEvent() {
    }

    public BusEvent(String event, Object data) {
        this.event = event;
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
