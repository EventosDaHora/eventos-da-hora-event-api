package com.eventosdahora.event.ms.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TicketDTO {

    private Long ticketId;

    private Long qtdTicket;
}
