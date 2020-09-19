package com.eventosdahora.event.ms.kafka;

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
    private EventService eventService;

    @Incoming("tickets")
    @Outgoing("envia-resposta")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public Uni<Message<Order>> processa(Message<Order> order) {
        log.info("Pedido que chegou do tópico 'executa-reserva-tickets': " + order.getPayload());
        return eventService
                .handleOrder(order.getPayload())
                .map(order::withPayload);
    }

    @Incoming("tickets-rollback")
    @Outgoing("envia-resposta")
    public Order rollback(Order order) {
        log.info("Pedido que chegou do tópico 'executa-reserva-ticket-rollback': " + order);

        if (new Random().nextInt(100) < 80) {
            order.setEvent(OrderEvent.TICKET_RESTAURADO_APROVADO);
        } else {
            order.setEvent(OrderEvent.TICKET_RESTAURADO_NEGADO);
        }
        return order;
    }
}
