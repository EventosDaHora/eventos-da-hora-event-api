package com.eventosdahora.event.ms.dto;

import com.eventosdahora.event.ms.dominio.ImageEvent;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ImageEventDTO {
	
	private String imageId;
	
	private String imageType;

	public ImageEvent toEntity() {
		return ImageEvent.builder()
				.imageId(imageId)
				.imageType(imageType)
				.build();
	}
}
