package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.service.EventKafkaService;
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
public class TicketKafkaHandler {
	
	@Inject
	EventKafkaService eventKafkaService;
	
	//@Incoming("tickets")
	//@Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
	//@Outgoing("envia-resposta")
	//@Blocking
	//@Transactional
	public OrderDTO processor(OrderDTO orderDTO) throws Exception {
		log.info("Pedido que chegou do tópico 'executa-reserva-tickets': " + orderDTO);
		return eventKafkaService.handleOrder(orderDTO);
	}


}
