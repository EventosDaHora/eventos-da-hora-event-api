package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.kafka.OrderEvent;
import com.eventosdahora.event.ms.repository.EventRepository;
import com.eventosdahora.event.ms.repository.TicketRepository;
import com.eventosdahora.event.ms.repository.TicketReservedRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
public class EventKafkaService {
    @Inject
    EventRepository eventRepository;

    @Inject
    TicketReservedRepository ticketReservedRepository;

    @Inject
    TicketRepository ticketRepository;

    public OrderDTO handleOrder(OrderDTO orderDTO) throws Exception {
        if (OrderEvent.RESERVAR_TICKET.equals(orderDTO.getOrderEvent())) {
            return reservaTicket(orderDTO);
        }

        if (OrderEvent.CONSOLIDAR_COMPRA.equals(orderDTO.getOrderEvent())) {
            return consolidaCompra(orderDTO);
        }

        if (OrderEvent.RESTAURAR_TICKET.equals(orderDTO.getOrderEvent())) {
            return restauraTicket(orderDTO);
        }

        throw new Exception("--- Invalid event: " + orderDTO.getOrderEvent());
    }

    @Transactional
    private OrderDTO reservaTicket(OrderDTO orderDTO) {
        boolean isOk = true;

        if (!isExistTickets(orderDTO.getTickets())) {
            orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_NEGADO);
            return orderDTO;
        }

        for (TicketDTO ticketDTO : orderDTO.getTickets()) {
            log.info("OrderID: " + orderDTO.getOrderId());
            log.info("TicketID: " + ticketDTO.getId());
            Long qtdReservedTickets = ticketReservedRepository.findQtdReservedTickets(ticketDTO.getId());
            log.info("Quantidade reservada: " + qtdReservedTickets);

            Ticket ticket = ticketRepository.findById(ticketDTO.getId());
            long qtdAvailableTickets = ticket.getInitialQuantity() - qtdReservedTickets;
            log.info("Quantidade disponível após cálculo: " + qtdAvailableTickets);

            if (qtdAvailableTickets <= ticketDTO.getQuantity()) {
                log.severe("Reserva negada");
                orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_NEGADO);
                isOk = false;
                break;
            }
        }

        if (isOk) {
            for (TicketDTO ticketDTO : orderDTO.getTickets()) {
                Ticket ticket = ticketRepository.findById(ticketDTO.getId());
                TicketReserved ticketReserved = TicketReserved.builder()
                        .confirmed(false)
                        .orderId(orderDTO.getOrderId())
                        .qtdTicketsReserved(ticketDTO.getQuantity())
                        .expirationDate(LocalDateTime.now().plusHours(1))
                        .ticket(ticket)
                        .build();

                ticketReservedRepository.persist(ticketReserved);
            }
            orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_APROVADO);
            log.info("Reserva com sucesso");
        }

        log.info("--- Reply channel: " + orderDTO);
        return orderDTO;
    }

    @Transactional
    private boolean isExistTickets(final List<TicketDTO> tickets) {
        List<Long> ids = tickets.stream().map(TicketDTO::getId).collect(Collectors.toList());
        return ticketRepository.exist(ids);
    }

    @Transactional
    private OrderDTO restauraTicket(OrderDTO orderDTO) {
        try {
            orderDTO.getTickets().forEach(
                    ticketDTO -> ticketReservedRepository.resturaTicket(ticketDTO.getId(), orderDTO.getOrderId()));
            orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
        } catch (PersistenceException pe) {
            orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_NEGADO);
            log.info("--- Reply channel: " + orderDTO);
            return orderDTO;
        }
        orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
        log.info("--- Reply channel: " + orderDTO);
        return orderDTO;
    }

    @Transactional
    private OrderDTO consolidaCompra(OrderDTO orderDTO) {
        orderDTO.getTickets().forEach(
                ticketDTO -> ticketReservedRepository.consolidaCompra(ticketDTO.getId(), orderDTO.getOrderId()));
        orderDTO.setOrderEvent(OrderEvent.CONSOLIDACAO_COMPRA_APROVADO);
        log.info("--- Reply channel: " + orderDTO);
        return orderDTO;
    }
}
