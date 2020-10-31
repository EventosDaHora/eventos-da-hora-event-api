package com.eventosdahora.event.ms.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_section")
public class Section {
	
	@Id
	@SequenceGenerator(name = "seq_section", sequenceName = "seq_section", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_section")
	@Column(name = "id_section")
	public Long id;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_event")
	public Event event;
	
	@Column(name = "nm_section")
	public String name;
	
	@Column(name = "ds_section", length = 500)
	public String description;
	
	@Column(name = "vl_amount")
	public BigDecimal ammount;
	
	public String metadata;
	
	@OneToMany(targetEntity = Ticket.class, mappedBy = "section", cascade = CascadeType.PERSIST)
	public List<Ticket> tickets;
	
}
