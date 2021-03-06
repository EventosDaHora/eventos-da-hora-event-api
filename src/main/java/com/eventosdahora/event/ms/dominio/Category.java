package com.eventosdahora.event.ms.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category {
	
	@Id
	@SequenceGenerator(name = "seq_category", sequenceName = "seq_category", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_category")
	@Column(name = "id_category")
	private Long id;
	
	@Column(name = "nm_category", unique = true)
	private String name;
	
	@Column(name = "ds_categoria", nullable = false)
	private String description;
}
