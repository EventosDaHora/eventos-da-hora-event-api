package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.dominio.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketDTO {
    
    private Long id;
    private Long quantity;

    public Ticket toEntity() {
        Ticket ticket = new Ticket();
        ticket.setInitialQuantity(quantity);
        return ticket;
    }

    public TicketDTO(Long quantity) {
        this.quantity = quantity;
    }
}
