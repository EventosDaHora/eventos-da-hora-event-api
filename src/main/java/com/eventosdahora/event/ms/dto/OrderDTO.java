package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.kafka.OrderEvent;
import com.eventosdahora.event.ms.kafka.OrderState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
	
	public static final String IDENTIFICADOR = "ID_PEDIDO";
	
	private Long orderId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdDate;
	
	private OrderState orderState;
	
	private OrderEvent orderEvent;
	
	private BigDecimal fees;
	
	private Long userId;
	
	@Builder.Default
	private List<TicketDTO> tickets = new ArrayList<>();
	
	private PaymentDTO payment;
}
