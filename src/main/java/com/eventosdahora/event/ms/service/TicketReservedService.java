package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.repository.TicketReservedRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Log
@ApplicationScoped
public class TicketReservedService extends GenericService<TicketReserved> {
	
	@Inject
	TicketReservedRepository ticketReservedRepository;
	
	public List<TicketReserved> findAllTicketReservedByOrderId(Long orderId){
		return ticketReservedRepository.findAllTicketReservedByOrderId(orderId);
	}

	@Override
	public PanacheRepository<TicketReserved> getRepository() {
		return ticketReservedRepository;
	}
}
