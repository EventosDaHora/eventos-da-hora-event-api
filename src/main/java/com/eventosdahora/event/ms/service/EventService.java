package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.kafka.OrderEvent;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Log
@ApplicationScoped
public class EventService {

    @Transactional
    public Uni<OrderDTO> handleOrder(OrderDTO orderDTO) {
        if (OrderEvent.RESERVAR_TICKET.equals(orderDTO.getOrderEvent())) {
            return reservaTicket(orderDTO);
        }

        if (OrderEvent.CONSOLIDAR_COMPRA.equals(orderDTO.getOrderEvent())) {
            orderDTO.setOrderEvent(OrderEvent.CONSOLIDACAO_COMPRA_APROVADO);
        }

        return null;
    }

    private Uni<OrderDTO> reservaTicket(OrderDTO orderDTO) {
        boolean isOk = false;
        for (TicketDTO ticketDTO : orderDTO.getTickets()) {
            Long qtdAvailableTickets = TicketReserved.findQtdAvailableTickets(ticketDTO.getTicketId(), orderDTO.getOrderId());
            Ticket ticket = Ticket.findById(ticketDTO.getTicketId());
            qtdAvailableTickets = ticket.qtdInicial - qtdAvailableTickets;

            if (qtdAvailableTickets >= ticketDTO.getQtdTicket()) {
                isOk = true;
                break;
            }
        }

        if (isOk) {
            for (TicketDTO ticketDTO : orderDTO.getTickets()) {
                Ticket ticket = Ticket.findById(ticketDTO.getTicketId());
                orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_APROVADO);
                TicketReserved ticketReserved = TicketReserved.builder()
                        .confirmed(false)
                        .orderId(orderDTO.getOrderId())
                        .qtdTicketsReserved(ticketDTO.getQtdTicket())
                        .expirationDate(LocalDateTime.now().plusHours(1))
                        .ticket(ticket)
                        .build();

                TicketReserved.persist(ticketReserved);
            }
        }

        return Uni.createFrom().item(orderDTO);
    }

    private Uni<OrderDTO> restauraTicket(OrderDTO orderDTO) {
        return null;
    }

    private Uni<OrderDTO> consolidaCompra(OrderDTO orderDTO) {
        return null;
    }
}
