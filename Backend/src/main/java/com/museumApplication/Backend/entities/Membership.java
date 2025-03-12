package com.museumApplication.Backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "membership_id")
public class Membership {

//    membership_id (PK)
//    user_id (FK) ➡️ User
//    membership_tier
//    start_date
//     end_date
//    status

    @Id
    @GeneratedValue
    private UUID membership_id;
    private String membership_tier;
    private Date start_date;
    private Date end_date;

    public UUID getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(UUID membership_id) {
        this.membership_id = membership_id;
    }

    public String getMembership_tier() {
        return membership_tier;
    }

    public void setMembership_tier(String membership_tier) {
        this.membership_tier = membership_tier;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean status;

    @OneToOne(mappedBy = "membership")
    private Users user;


    public Membership(){
        this.membership_id = UUID.randomUUID();
    }
}
