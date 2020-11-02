package com.eventosdahora.event.ms.dominio;

import lombok.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_image_event")
public class ImageEvent {
	
	@Id
	@SequenceGenerator(name = "seq_image_event", sequenceName = "seq_image_event", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_image_event")
	@Column(name = "id_image_event")
	private Long id;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_event")
	private Event event;
	
	@Column(name = "id_image")
	private String imageId;
	
	@Column(name = "ds_image_type")
	private String imageType;
}
