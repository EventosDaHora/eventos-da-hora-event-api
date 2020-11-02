package com.eventosdahora.event.ms.dominio;

import lombok.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_section")
public class Section {
	
	@Id
	@SequenceGenerator(name = "seq_section", sequenceName = "seq_section", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_section")
	@Column(name = "id_section")
	private Long id;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_event")
	private Event event;
	
	@Column(name = "nm_section")
	private String name;
	
	@Column(name = "ds_section", length = 500)
	private String description;
	
	@Column(name = "vl_amount")
	private BigDecimal ammount;
	
	private String metadata;
	
	@OneToMany(targetEntity = Ticket.class, mappedBy = "section", cascade = CascadeType.ALL)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	private List<Ticket> tickets = new ArrayList<>();

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
		ticket.setSection(this);
	}

	public void removeTicket(Ticket ticket) {
		tickets.remove(ticket);
		ticket.setSection(null);
	}
	
}
