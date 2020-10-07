package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.kafka.OrderEvent;
import com.eventosdahora.event.ms.kafka.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDTO {

    public static final String IDENTIFICADOR = "ID_PEDIDO";

    private Long orderId;

    private LocalDate createdDate;

    private OrderState orderState;

    private OrderEvent orderEvent;

    private BigDecimal fees;

    private Long userId;

    @Builder.Default
    private List<TicketDTO> tickets = new ArrayList<>();
    
    private PaymentDTO payment;
}
