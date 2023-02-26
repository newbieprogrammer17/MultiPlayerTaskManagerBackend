package com.b902.watersupply;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
public class WaterUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "has_voted")
    private char hasVoted;

    @Column(name = "in_queue")
    private char inQueue;

    @Column(name = "no_of_cycles_completed")
    private Integer noOfCyclesCompleted;

    public WaterUsers(int id, String username, String password, char hasVoted,char inQueue, Integer noOfCyclesCompleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hasVoted = hasVoted;
        this.inQueue = inQueue;
        this.noOfCyclesCompleted = noOfCyclesCompleted;
    }

    public WaterUsers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(char hasVoted) {
        this.hasVoted = hasVoted;
    }

    public char getInQueue() {
        return inQueue;
    }

    public void setInQueue(char inQueue) {
        this.inQueue = inQueue;
    }

    public Integer getNoOfCyclesCompleted() {
        return noOfCyclesCompleted;
    }

    public void setNoOfCyclesCompleted(Integer noOfCyclesCompleted) {
        this.noOfCyclesCompleted = noOfCyclesCompleted;
    }
}
