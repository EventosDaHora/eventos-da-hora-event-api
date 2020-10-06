package com.eventosdahora.event.ms.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentDTO {
	
	public BigDecimal vlAmount;
	public PaymentType paymentType;
	public Long paymentId;
	
}
