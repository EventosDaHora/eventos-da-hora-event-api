package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.service.EventService;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;

@Log
@ApplicationScoped
public class TicketKafkaHandler {

    @Inject
    EventService eventService;

    @Incoming("tickets")
    @Outgoing("envia-resposta")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public Uni<Message<OrderDTO>> processor(Message<OrderDTO> orderDTO) {
        log.info("Pedido que chegou do tópico 'executa-reserva-tickets': " + orderDTO.getPayload());

        return eventService
                .handleOrder(orderDTO.getPayload())
                .map(orderDTO::withPayload);
    }

    @Incoming("tickets-rollback")
    @Outgoing("envia-resposta")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public Uni<Message<OrderDTO>> rollback(Message<OrderDTO> orderDTO) {
        log.info("Pedido que chegou do tópico 'executa-reserva-ticket-rollback': " + orderDTO);

        return eventService
                .handleOrder(orderDTO.getPayload())
                .map(orderDTO::withPayload);
    }
}
