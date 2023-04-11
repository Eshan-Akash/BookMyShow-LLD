package com.scaler.bmsfairmysql.dto;

import com.scaler.bmsfairmysql.models.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDTO {
    private ResponseStatus responseStatus;
    private Ticket ticket;
}
