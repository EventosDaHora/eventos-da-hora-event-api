package com.eventosdahora.event.ms.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_image_event")
public class ImageEvent {
	
	@Id
	@SequenceGenerator(name = "seq_image_event", sequenceName = "seq_image_event", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_image_event")
	@Column(name = "id_image_event")
	public Long id;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_event")
	public Event event;
	
	@Column(name = "id_image")
	public String imageId;
	
	@Column(name = "ds_image_type")
	public String imageType;
}
