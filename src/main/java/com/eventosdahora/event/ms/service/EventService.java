package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.kafka.Order;
import com.eventosdahora.event.ms.kafka.OrderEvent;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class EventService {

    public Uni<Order> handleOrder(Order order) {
        return Uni.createFrom().item(order)
                .onItem().invoke(this::handle);
    }

    private void handle(Order order) {
        if (OrderEvent.RESERVAR_TICKET.equals(order.getEvent())) {
            if (new Random().nextInt(100) < 80)
                order.setEvent(OrderEvent.RESERVA_TICKET_APROVADO);
            else
                order.setEvent(OrderEvent.RESERVA_TICKET_NEGADO);

        } else if (OrderEvent.CONSOLIDAR_COMPRA.equals(order.getEvent())) {
            order.setEvent(OrderEvent.CONSOLIDACAO_COMPRA_APROVADO);
        }
    }

    private Uni<Order> reservaTicket(Order order) {
        return null;
    }

    private Uni<Order> restauraTicket(Order order) {
        return null;
    }

    private Uni<Order> consolidaCompra(Order order) {
        return null;
    }
}
