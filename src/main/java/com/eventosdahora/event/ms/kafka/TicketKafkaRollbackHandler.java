package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.service.EventService;
import io.smallrye.reactive.messaging.annotations.Blocking;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Log
@ApplicationScoped
public class TicketKafkaRollbackHandler {
	
	@Inject
	EventService eventService;
	
	@Incoming("tickets-rollback")
	@Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
	@Outgoing("envia-resposta")
	@Blocking
	@Transactional
	public OrderDTO rollback(OrderDTO orderDTO) throws Exception {
		log.info("Pedido que chegou do tópico 'executa-reserva-ticket-rollback': " + orderDTO);
		return eventService.handleOrder(orderDTO);
	}
}
