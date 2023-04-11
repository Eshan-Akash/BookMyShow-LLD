package com.scaler.bmsfairmysql.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {
    private String name;
    private String address;
    // T: A
    // 1: m
    // 1: 1
    // 1: m
    @OneToMany(mappedBy = "theatre")
    private List<Auditorium> auditoriums;

    // T: C
    // 1: 1
    // m: 1
    // m: 1
    @ManyToOne
    private City city;
}
