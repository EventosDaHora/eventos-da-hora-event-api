package com.eventosdahora.event.ms.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ticket_reserved")
public class TicketReserved {
    
    @Id
    @SequenceGenerator(name = "seq_ticket_reserved", sequenceName = "seq_ticket_reserved", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket_reserved")
    @Column(name = "id_ticket_reserved")
    private Long id;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

    @Column(name = "qtd_tickets_reserved")
    private Long qtdTicketsReserved;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "dt_expired")
    private LocalDateTime expirationDate;

    @Column(name = "id_order")
    private Long orderId;

    @Column(name = "id_confirmed", columnDefinition = "boolean default false")
    private Boolean confirmed;

 

}
