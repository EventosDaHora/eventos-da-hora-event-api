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
    public Uni<Message<OrderDTO>> processa(Message<OrderDTO> orderDTO) {
        log.info("Pedido que chegou do tópico 'executa-reserva-tickets': " + orderDTO.getPayload());

        return eventService
                .handleOrder(orderDTO.getPayload())
                .map(orderDTO::withPayload);
    }

    @Incoming("tickets-rollback")
    @Outgoing("envia-resposta")
    public OrderDTO rollback(OrderDTO orderDTO) {
        log.info("Pedido que chegou do tópico 'executa-reserva-ticket-rollback': " + orderDTO);

        if (new Random().nextInt(100) < 80) {
            orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
        } else {
            orderDTO.setOrderEvent(OrderEvent.TICKET_RESTAURADO_NEGADO);
        }
        return orderDTO;
    }
}
