package com.eventosdahora.event.ms.dominio;

import lombok.*;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ticket")
public class Ticket {
	
	@Id
	@SequenceGenerator(name = "seq_ticket", sequenceName = "seq_ticket", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
	@Column(name = "id_ticket")
	private Long id;
	
	@JsonbTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_section")
	private Section section;
	
	@Column(name = "qt_ticket_initial")
	private Long initialQuantity;
	
	@OneToMany(targetEntity = TicketReserved.class, mappedBy = "ticket", cascade = CascadeType.ALL)
	@Setter(AccessLevel.NONE)
	@Builder.Default
	private List<TicketReserved> ticketsReserved = new ArrayList<>();

	public void addTicketReserved(TicketReserved ticketReserved) {
		ticketsReserved.add(ticketReserved);
		ticketReserved.setTicket(this);
	}

	public void removeTicketReserved(TicketReserved ticketReserved) {
		ticketsReserved.remove(ticketReserved);
		ticketReserved.setTicket(null);
	}
}
