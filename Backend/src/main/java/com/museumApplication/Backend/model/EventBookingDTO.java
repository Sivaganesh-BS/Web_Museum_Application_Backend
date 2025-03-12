package com.museumApplication.Backend.model;

import com.museumApplication.Backend.entities.Payments;
import lombok.Data;

import java.util.UUID;

@Data
public class EventBookingDTO {


    private UUID eventId;
    private long quantity;
    private double amount;
    private Payments.Payment_Method payment_method;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payments.Payment_Method getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Payments.Payment_Method payment_method) {
        this.payment_method = payment_method;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "EventBookingDTO{" +
                "eventId=" + eventId +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", payment_method=" + payment_method +
                ", userId=" + userId +
                '}';
    }
}
