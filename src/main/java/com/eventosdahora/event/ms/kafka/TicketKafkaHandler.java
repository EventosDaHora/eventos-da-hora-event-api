package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.service.EventService;
import io.smallrye.reactive.messaging.annotations.Blocking;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Log
@ApplicationScoped
public class TicketKafkaHandler {
	
	@Inject
	EventService eventService;
	
	@Incoming("tickets")
	@Outgoing("envia-resposta")
	@Blocking
	@Transactional
	public OrderDTO processor(OrderDTO orderDTO) throws Exception {
		log.info("Pedido que chegou do tópico 'executa-reserva-tickets': " + orderDTO);
		return eventService.handleOrder(orderDTO);
	}
	
	@Incoming("tickets-rollback")
	@Outgoing("envia-resposta")
	public OrderDTO rollback(OrderDTO orderDTO) throws Exception {
		log.info("Pedido que chegou do tópico 'executa-reserva-ticket-rollback': " + orderDTO);
		return eventService.handleOrder(orderDTO);
	}
}
