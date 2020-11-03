package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.dominio.Section;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {
	
	private String name;

	private String description;
	
	private BigDecimal ammount;

	private Long qtdTickets;
	
	public Section toEntity() {
		return Section.builder()
				.name(name)
				.description(description)
				.ammount(ammount)
				.build();
	}
}
