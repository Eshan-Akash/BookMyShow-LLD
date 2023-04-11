package com.scaler.bmsfairmysql.models;


import com.scaler.bmsfairmysql.models.enums.SeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    // SS: S
    // 1: 1
    // m : 1
    // m: 1
    @ManyToOne
    private Show show;

    // SS: S
    // 1: 1
    // m: 1
    // m: 1
    @ManyToOne
    private Seat seat;

    private Date lockedAt;

    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;
}
