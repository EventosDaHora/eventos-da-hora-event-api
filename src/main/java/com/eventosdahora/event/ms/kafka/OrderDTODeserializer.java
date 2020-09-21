package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.dto.OrderDTO;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class OrderDTODeserializer extends JsonbDeserializer<OrderDTO> {

    public OrderDTODeserializer() {
        super(OrderDTO.class);
    }
}
