package com.scaler.bmsfairmysql.models;


import com.scaler.bmsfairmysql.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel {
    private String name;

    @ManyToOne
    private Theatre theatre;

    @OneToMany
    private List<Seat> seats;

    // A: S
    // 1: m
    // 1: 1
    // 1: m
    @OneToMany
    private List<Show> shows;

    // skipping - enums have a different attribute
    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Feature> featuresSupported;
}
