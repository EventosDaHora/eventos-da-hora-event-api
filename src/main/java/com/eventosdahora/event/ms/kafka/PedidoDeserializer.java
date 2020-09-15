package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dominio.Pedido;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class PedidoDeserializer extends JsonbDeserializer<Pedido> {

    public PedidoDeserializer() {
        super(Pedido.class);
    }
}
