package com.museumApplication.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "event_id")
public class Event {
//    event_id (PK)
//    title
//    description
//    event_date
//    location
//    type (online/offline)
//    price_per_ticket
//    max_capacity
//    available_seats

    @Id
    @GeneratedValue
    private UUID event_id;
    private String title;
    private String description;
    private Date event_date;
    private String location;
    @Enumerated(EnumType.STRING)
    private Event_Type event_type;
    private double price_per_ticket;
    private long max_capacity;
    private long available_seats;


    public Event(String title, String description, Date event_date, String location, Event_Type event_type, double price_per_ticket, long max_capacity, long available_seats) {
        this.title = title;
        this.description = description;
        this.event_date = event_date;
        this.location = location;
        this.event_type = event_type;
        this.price_per_ticket = price_per_ticket;
        this.max_capacity = max_capacity;
        this.available_seats = available_seats;
        this.event_id= UUID.randomUUID();
    }

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

    public Event_Type getEvent_type() {
        return event_type;
    }

    public void setEvent_type(Event_Type event_type) {
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;


    public Event(){
        this.event_id= UUID.randomUUID();
    }

    public enum Event_Type{
        ONLINE , OFFLINE
    }
}
