package com.eventosdahora.event.ms.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ImageEventDTO {
	
	private Long id;
	
	private String imageId;
	
	private String imageType;
}
