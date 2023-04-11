package com.scaler.bmsfairmysql.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    private Date bookedAt;
    // T: BB
    // 1: 1
    // m: 1
    // m: 1
    @ManyToOne
    private User bookedBy;
    private int amount;

    // T: SS
    // 1: m
    // 1: 1
    // 1: m
    @OneToMany
    private List<ShowSeat> showSeats;

    // T: P
    // 1: 1
    // 1: 1
    // 1: 1
    @OneToOne
    private Payment payment;
}
