package com.scaler.bmsfairmysql.models;


import com.scaler.bmsfairmysql.models.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String number;
    private int row;
    private int col;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;
}
