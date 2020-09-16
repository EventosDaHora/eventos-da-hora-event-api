package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dominio.PedidoEvent;
import com.eventosdahora.event.ms.dominio.Pedido;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@Log
@ApplicationScoped
public class TicketKafka {

    @Incoming("tickets")
    @Outgoing("envia-resposta")
    public Pedido processa(Pedido pedido) {
        log.info("Pedido que chegou do tópico 'executa-reserva-tickets': " + pedido);

        if (PedidoEvent.RESERVAR_TICKET.equals(pedido.getEvent())) {
            if (new Random().nextInt(100) < 80)
                pedido.setEvent(PedidoEvent.RESERVA_TICKET_APROVADO);
            else
                pedido.setEvent(PedidoEvent.RESERVA_TICKET_NEGADO);

        } else if (PedidoEvent.CONSOLIDAR_COMPRA.equals(pedido.getEvent())) {
            pedido.setEvent(PedidoEvent.CONSOLIDACAO_COMPRA_APROVADO);
        }
        return pedido;
    }

    @Incoming("tickets-rollback")
    @Outgoing("envia-resposta")
    public Pedido rollback(Pedido pedido) {
        log.info("Pedido que chegou do tópico 'executa-reserva-ticket-rollback': " + pedido);
        pedido.setEvent(PedidoEvent.TICKET_RESTAURADO_APROVADO);
        return pedido;
    }
}
