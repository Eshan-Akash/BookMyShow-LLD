package com.scaler.bmsfairmysql.models;


import com.scaler.bmsfairmysql.models.enums.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String name;
    private String desc;

    @Enumerated(EnumType.ORDINAL)
    private Language language;

    @ManyToMany
    private List<Actor> actors;
}

// Movie : Actor
// 1: m
// m: 1
// m: m
