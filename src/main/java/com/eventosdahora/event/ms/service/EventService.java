package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Event;
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
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
public class EventService {

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
			Long qtdAvailableTickets = ticketReservedRepository.findQtdAvailableTickets(ticketDTO.getId(), orderDTO.getOrderId());
			Ticket ticket = ticketRepository.findById(ticketDTO.getId());
			qtdAvailableTickets = ticket.initialQuantity - qtdAvailableTickets;

			if (qtdAvailableTickets <= ticketDTO.getQuantity()) {
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

	public Event getRandomEvent() {
		Random random = new Random();
		int totalEvents = Integer.parseInt(Long.valueOf(eventRepository.count()).toString()) + 1;
		int randomNumber = random.nextInt(totalEvents);
		return eventRepository.findById((long) randomNumber);
	}

	public Optional<Event> findById(final Long eventId) {
		return eventRepository.findByIdOptional(eventId);
	}

	public List<Event> listAll() {
		return eventRepository.listAll();
	}
}
