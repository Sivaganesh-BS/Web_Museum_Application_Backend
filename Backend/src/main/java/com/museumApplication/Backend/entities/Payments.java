package com.museumApplication.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "payment_id")
public class Payments {

//    payment_id (PK)
//    user_id (FK) ➡️ User
//    amount
//    payment_date
//    status
//    payment_method
//    reference_number

    @Id
    @GeneratedValue
    private UUID payment_id;
    private double amount;
    private Date payment_date;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean status;
    @Enumerated(EnumType.STRING)
    private Payment_Method payment_method;
    @GeneratedValue
    private String  reference_number;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;


    public UUID getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(UUID payment_id) {
        this.payment_id = payment_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Payment_Method getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(Payment_Method payment_method) {
        this.payment_method = payment_method;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Payments(){
        this.payment_id = UUID.randomUUID();
        this.reference_number = generateReferenceNumber();
    }

    public Payments(double amount, Payment_Method payment_method, Users user) {
        this.payment_id = UUID.randomUUID();
        this.reference_number = generateReferenceNumber();
        this.payment_date = new Date(System.currentTimeMillis());
        this.amount = amount;
        this.payment_method = payment_method;
        this.user = user;
        this.status=true;
    }

    public enum Payment_Method{
        CARD,CASH,ONLINE,UPI
    }

    private  String generateReferenceNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH.mm.ss");
        String timestamp = LocalDateTime.now().format(formatter);
        int random = new Random().nextInt(900) + 100;
        return "ORD = " + timestamp + " = " + random;
    }
}
