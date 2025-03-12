package com.museumApplication.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ticket_id")
public class Ticket {

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", booking_date=" + booking_date +
                ", quantity=" + quantity +
                ", total_price=" + total_price +
                ", payment_status=" + payment_status +
                ", user=" + user +
                ", event=" + event +
                '}';
    }

    //    ticket_id (PK)
//    user_id (FK) ➡️ User
//    event_id (FK) ➡️ Event
//    booking_date
//    quantity
//    total_price
//    payment_status
    @Id
    @GeneratedValue
    private UUID ticket_id;
    private Date booking_date;
    private long quantity;
    private double total_price;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean payment_status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public UUID getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(UUID ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public boolean isPayment_status() {
        return payment_status;
    }

    public void setPayment_status(boolean payment_status) {
        this.payment_status = payment_status;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Ticket(long quantity, double total_price, Users user, Event event, boolean payment_status) {
        this.quantity = quantity;
        this.total_price = total_price;
        this.user = user;
        this.event = event;
        this.payment_status = payment_status;
        this.ticket_id = UUID.randomUUID();
        this.booking_date = new Date(System.currentTimeMillis());
    }

    public Ticket(){
        this.ticket_id = UUID.randomUUID();
    }


}
