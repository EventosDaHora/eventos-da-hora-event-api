package com.eventosdahora.event.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
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
public class Ticket extends PanacheEntity {
	
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
