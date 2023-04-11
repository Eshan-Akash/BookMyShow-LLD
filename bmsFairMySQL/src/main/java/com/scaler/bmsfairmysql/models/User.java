package com.scaler.bmsfairmysql.models;


import com.scaler.bmsfairmysql.models.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String password;

    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    private List<Role> roles;
}
