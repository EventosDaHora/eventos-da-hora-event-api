package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.quarkus.panache.common.Parameters.with;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TicketReserved extends PanacheEntity {

    @Column(name = "id_ticket_reserved")
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    public Ticket ticket;

    @Column(name = "qtd_tickets_reserved")
    public Long qtdTicketsReserved;

    @Column(name = "dt_expired")
    public LocalDateTime expirationDate;

    @Column(name = "id_order")
    public Long orderId;

    @Column(name = "id_confirmed", columnDefinition = "boolean default false")
    public Boolean confirmed;

    public static Long findQtdAvailableTickets(Long ticketId, Long orderId) {
        String sql = " SELECT tr FROM TicketReserved tr"
                   + " JOIN tr.ticket t"
                   + " WHERE t.id = :id"
                   + " AND orderId = :orderId"
                   + " AND tr.confirmed = false"
                   + " AND :hoje <= tr.expirationDate";

        return find(sql, with("id", ticketId)
                    .and("hoje", LocalDate.now())
                    .and("orderId", orderId))
                .count();
    }

}
