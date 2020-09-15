package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dominio.PedidoEvent;
import com.eventosdahora.event.ms.dominio.Pedido;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@Log
@ApplicationScoped
public class TicketKafka {

    @Incoming("tickets")
    @Outgoing("envia-resposta")
    public Pedido processa(Pedido pedido) {
        log.info("Pedido que chegou do tópico: " + pedido);
        pedido.setEvent(PedidoEvent.RESERVA_TICKET_APROVADO);
        return pedido;
    }
}
