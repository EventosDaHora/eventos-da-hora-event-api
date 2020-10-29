package com.eventosdahora.event.ms.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static io.quarkus.panache.common.Parameters.with;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TicketReserved extends PanacheEntity {

    @Column(name = "id_ticket_reserved")
    public Long id;
    
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    public Ticket ticket;

    @Column(name = "qtd_tickets_reserved")
    public Long qtdTicketsReserved;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "dt_expired")
    public LocalDateTime expirationDate;

    @Column(name = "id_order")
    public Long orderId;

    @Column(name = "id_confirmed", columnDefinition = "boolean default false")
    public Boolean confirmed;

    private static final String SQL_AVAILABLE
            = " SELECT tr FROM TicketReserved tr"
            + " JOIN tr.ticket t"
            + " WHERE t.id = :id"
            + " AND tr.orderId = :orderId"
            + " AND tr.confirmed = :isConfirmed"
            + " AND :hoje <= tr.expirationDate";

    public static Long findQtdAvailableTickets(Long ticketId, Long orderId) {
        return find(SQL_AVAILABLE, with("id", ticketId)
                    .and("hoje", LocalDateTime.now())
                    .and("orderId", orderId)
                    .and("isConfirmed", false)).count();
    }

    public static void consolidaCompra(Long ticketId, Long orderId) {
        TicketReserved.update("confirmed = true WHERE orderId = :orderId AND ticket.id = :ticketId",
                parameters(ticketId, orderId));

        TicketReserved.update("confirmed = true WHERE orderId = :orderId AND ticket.id = :ticketId",
                parameters(ticketId, orderId));
    }

    public static void resturaTicket(Long ticketId, Long orderId) {
       TicketReserved.delete("orderId = :orderId AND ticket.id = :ticketId",
               parameters(ticketId, orderId));
    }

    private static Parameters parameters(Long ticketId, Long orderId) {
        return with("orderId", orderId).and("ticketId", ticketId);
    }
    
    public static List<TicketReserved> findAllTicketReservedByOrderId(Long orderId){
        return find("orderId", orderId).list();
    }

}
