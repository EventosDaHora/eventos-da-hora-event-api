package com.eventosdahora.ticket.ms.kafka;

import com.eventosdahora.ticket.ms.dominio.Pedido;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class PedidoDeserializer extends JsonbDeserializer<Pedido> {

    public PedidoDeserializer() {
        super(Pedido.class);
    }
}
