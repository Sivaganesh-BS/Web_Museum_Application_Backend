package com.museumApplication.Backend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "user_id")
public class Users {

//    user_id (PK)
//    username
//    email
//    password_hash
//    membership_tier
//    registration_date
//    status
//    role

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String password;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMembership_tier() {
        return membership_tier;
    }

    public void setMembership_tier(String membership_tier) {
        this.membership_tier = membership_tier;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public List<CommunityPost> getCommunityPosts() {
        return communityPosts;
    }

    public void setCommunityPosts(List<CommunityPost> communityPosts) {
        this.communityPosts = communityPosts;
    }

    private String email;
    private String membership_tier;
    private Date registration_date;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean status;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Payments> payments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_id", referencedColumnName = "membership_id")
    private Membership membership;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommunityPost> communityPosts;

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comment;


    public enum Role{
        ADMIN,USER
    }
}
