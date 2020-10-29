package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.kafka.OrderEvent;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log
@ApplicationScoped
public class EventService {
	
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
			Long qtdAvailableTickets = TicketReserved.findQtdAvailableTickets(ticketDTO.getId(), orderDTO.getOrderId());
			Ticket ticket = Ticket.findById(ticketDTO.getId());
			qtdAvailableTickets = ticket.qtdInicial - qtdAvailableTickets;
			
			if (qtdAvailableTickets <= ticketDTO.getQuantity()) {
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
				                                              .qtdTicketsReserved(ticketDTO.getQuantity())
				                                              .expirationDate(LocalDateTime.now().plusHours(1))
				                                              .ticket(ticket)
				                                              .build();
				
				TicketReserved.persist(ticketReserved);
			}
			orderDTO.setOrderEvent(OrderEvent.RESERVA_TICKET_APROVADO);
		}
		
		log.info("--- Reply channel: " + orderDTO);
		return orderDTO;
	}
	
	@Transactional
	private boolean isExistTickets(final List<TicketDTO> tickets) {
		
		for (TicketDTO ticket : tickets) {
			Optional<PanacheEntityBase> byIdOptional = Ticket.findByIdOptional(ticket.getId());
			if (!byIdOptional.isPresent()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Transactional
	private OrderDTO restauraTicket(OrderDTO orderDTO) {
//		try {
//			orderDTO.getTickets().forEach(
//					ticketDTO -> TicketReserved.resturaTicket(ticketDTO.getId(), orderDTO.getOrderId()));
//			orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
//		} catch (PersistenceException pe) {
//			orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_NEGADO);
//			log.info("--- Reply channel: " + orderDTO);
//			return orderDTO;
//		}
		orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
		log.info("--- Reply channel: " + orderDTO);
		return orderDTO;
	}
	
	@Transactional
	private OrderDTO consolidaCompra(OrderDTO orderDTO) {
		orderDTO.getTickets().forEach(
				ticketDTO -> TicketReserved.consolidaCompra(ticketDTO.getId(), orderDTO.getOrderId()));
		orderDTO.setOrderEvent(OrderEvent.CONSOLIDACAO_COMPRA_APROVADO);
		log.info("--- Reply channel: " + orderDTO);
		return orderDTO;
	}
}
