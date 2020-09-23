package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.kafka.OrderEvent;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Log
@ApplicationScoped
public class EventService {

    public Uni<OrderDTO> handleOrder(OrderDTO orderDTO) {
        if (OrderEvent.RESERVAR_TICKET.equals(orderDTO.getOrderEvent())) {
            return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> reservaTicket(orderDTO)));
        }

        if (OrderEvent.CONSOLIDAR_COMPRA.equals(orderDTO.getOrderEvent())) {
           return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> consolidaCompra(orderDTO)));
        }

        if (OrderEvent.RESTAURAR_TICKET.equals(orderDTO.getOrderEvent())) {
            return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> restauraTicket(orderDTO)));
        }

        return Uni.createFrom().failure(() -> new Exception("Estado inválido"));
    }

    @Transactional
    private OrderDTO reservaTicket(OrderDTO orderDTO) {
        boolean isOk = true;

        for (TicketDTO ticketDTO : orderDTO.getTickets()) {
            Long qtdAvailableTickets = TicketReserved.findQtdAvailableTickets(ticketDTO.getId(), orderDTO.getOrderId());
            Ticket ticket = Ticket.findById(ticketDTO.getId());
            qtdAvailableTickets = ticket.qtdInicial - qtdAvailableTickets;

            if (qtdAvailableTickets <= ticketDTO.getAmmount()) {
                orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_NEGADO);
                isOk = false;
                break;
            }
        }

        if (isOk) {
            for (TicketDTO ticketDTO : orderDTO.getTickets()) {
                Ticket ticket = Ticket.findById(ticketDTO.getId());
                TicketReserved ticketReserved = TicketReserved.builder()
                        .confirmed(false)
                        .orderId(orderDTO.getOrderId())
                        .qtdTicketsReserved(ticketDTO.getAmmount())
                        .expirationDate(LocalDateTime.now().plusHours(1))
                        .ticket(ticket)
                        .build();

                TicketReserved.persist(ticketReserved);
            }
            orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_APROVADO);
        }

        return orderDTO;
    }

    @Transactional
    private OrderDTO restauraTicket(OrderDTO orderDTO) {
        try {
            orderDTO.getTickets().forEach(ticketDTO -> TicketReserved.resturaTicket(ticketDTO.getId(), orderDTO.getOrderId()));
            orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
        } catch (PersistenceException pe) {
            orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_NEGADO);
            return orderDTO;
        }
        return orderDTO;
    }

    @Transactional
    private OrderDTO consolidaCompra(OrderDTO orderDTO) {
        orderDTO.getTickets().forEach(ticketDTO -> TicketReserved.consolidaCompra(ticketDTO.getId(), orderDTO.getOrderId()));
        orderDTO.setOrderEvent(OrderEvent.CONSOLIDACAO_COMPRA_APROVADO);
        return orderDTO;
    }
}
