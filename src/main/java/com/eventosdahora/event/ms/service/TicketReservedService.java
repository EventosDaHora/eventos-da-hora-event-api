package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.repository.TicketReservedRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Log
@ApplicationScoped
public class TicketReservedService {
	
	@Inject
	private TicketReservedRepository ticketReservedRepository;
	
	public List<TicketReserved> findAllTicketReservedByOrderId(Long orderId){
		return ticketReservedRepository.findAllTicketReservedByOrderId(orderId);
	}
	
	public List<TicketReserved> listAll(){
		return ticketReservedRepository.listAll();
	}
}
