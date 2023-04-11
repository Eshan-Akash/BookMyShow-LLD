package com.scaler.bmsfairmysql.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel {
    private String name;
    // C: T
    // 1: m
    // 1: 1
    // 1: m
    @OneToMany(mappedBy="city")
    private List<Theatre> theatres;
}
