package com.scaler.bmsfairmysql.models;


import com.scaler.bmsfairmysql.models.enums.PaymentProvider;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private String transactionNumber;


    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;

    @OneToOne
    private Ticket ticket;
}
