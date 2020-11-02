package com.eventosdahora.event.ms.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ticket")
public class Ticket {
	
	@Id
	@SequenceGenerator(name = "seq_ticket", sequenceName = "seq_ticket", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
	@Column(name = "id_ticket")
	public Long id;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_section")
	public Section section;
	
	@Column(name = "qt_ticket_initial")
	public Long initialQuantity;
	
	@OneToMany(targetEntity = TicketReserved.class, mappedBy = "ticket", cascade = CascadeType.PERSIST)
	public List<TicketReserved> ticketReserved;


}
