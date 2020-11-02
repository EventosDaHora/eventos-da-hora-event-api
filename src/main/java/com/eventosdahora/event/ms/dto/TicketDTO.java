package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.dominio.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    
    private Long id;
    private Long quantity;

    public Ticket toEntity() {
        return Ticket.builder()
                .initialQuantity(quantity)
                .build();
    }
}
