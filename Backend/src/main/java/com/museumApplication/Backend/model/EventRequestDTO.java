package com.museumApplication.Backend.model;

import com.museumApplication.Backend.entities.Event;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class EventRequestDTO {

    private UUID event_id=null;
    private String title;
    private String description;
    private Date event_date;
    private String location;
    private Event.Event_Type event_type;
    private double price_per_ticket;
    private long max_capacity;
    private long available_seats;


    public UUID getEvent_id() {
        return event_id;
    }

    public void setEvent_id(UUID event_id) {
        this.event_id = event_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Event.Event_Type getEvent_type() {
        return event_type;
    }

    public void setEvent_type(Event.Event_Type event_type) {
        this.event_type = event_type;
    }

    public double getPrice_per_ticket() {
        return price_per_ticket;
    }

    public void setPrice_per_ticket(double price_per_ticket) {
        this.price_per_ticket = price_per_ticket;
    }

    public long getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(long max_capacity) {
        this.max_capacity = max_capacity;
    }

    public long getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(long available_seats) {
        this.available_seats = available_seats;
    }


}
