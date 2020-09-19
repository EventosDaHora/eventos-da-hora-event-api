package com.eventosdahora.event.ms.kafka;

import com.eventosdahora.event.ms.kafka.OrderEvent;
import com.eventosdahora.event.ms.kafka.OrderState;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    public static final String IDENTIFICADOR = "ID_PEDIDO";

    private Long id;

    private OrderState state;

    private OrderEvent event;
}
